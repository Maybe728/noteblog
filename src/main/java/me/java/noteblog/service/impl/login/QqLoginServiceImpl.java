package me.java.noteblog.service.impl.login;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import me.java.noteblog.constant.NBV5;
import me.java.noteblog.constant.OperateType;
import me.java.noteblog.constant.RoleEnum;
import me.java.noteblog.service.interfaces.LoginService;
import me.java.noteblog.service.interfaces.UserService;
import me.java.noteblog.service.interfaces.property.ParamService;
import me.java.noteblog.mapper.UserCoinRecordMapper;
import me.java.noteblog.model.ResultBean;
import me.java.noteblog.model.bo.login.QqLoginData;
import me.java.noteblog.model.entity.User;
import me.java.noteblog.model.entity.UserCoinRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service("qqLoginService")
public class QqLoginServiceImpl implements LoginService<ResultBean, QqLoginData> {

    private final UserService userService;
    private final ParamService paramService;
    private final UserCoinRecordMapper userCoinRecordMapper;

    @Autowired
    public QqLoginServiceImpl(UserService userService, ParamService paramService, UserCoinRecordMapper userCoinRecordMapper) {
        this.userService = userService;
        this.paramService = paramService;
        this.userCoinRecordMapper = userCoinRecordMapper;
    }

    @Override
    public ResultBean doLogin(QqLoginData data) {
        try {
            String appId = paramService.findByName(NBV5.QQ_APP_ID).getValue();
            String appKey = paramService.findByName(NBV5.QQ_APP_KEY).getValue();

            Map<String, Object> p1 = MapUtil.of("grant_type", "authorization_code");
            p1.put("client_id", appId);
            p1.put("client_secret", appKey);
            p1.put("code", data.getCode());
            p1.put("redirect_uri", data.getCallbackDomain());

            String resp1 = HttpUtil.get("https://graph.qq.com/oauth2.0/token", p1);
            String accessToken = resp1.substring(13, resp1.length() - 66);
            String callback = HttpUtil.get("https://graph.qq.com/oauth2.0/me", MapUtil.of("access_token", accessToken));
            String openId = callback.substring(45, callback.length() - 6);

            Map<String, Object> p2 = MapUtil.of("access_token", accessToken);
            p2.put("oauth_consumer_key", appId);
            p2.put("openid", openId);

            JSONObject json2 = JSONUtil.parseObj(HttpUtil.get("https://graph.qq.com/user/get_user_info", p2));
            if (json2.getInt("ret") == 0) {
                User user = userService.findByQqOpenId(openId, true);
                if (user != null) {
                    return ResultBean.ok("授权成功！", "/").put(NBV5.SESSION_USER_KEY, user);
                } else {
                    User lockedUser = userService.findByQqOpenId(openId, false);
                    if (lockedUser != null) {
                        return ResultBean.error("QQ登录授权失败，原因：用户已被锁定！");
                    }
                    String nickname = json2.getStr("nickname");
                    int cnt = userService.countNickname(nickname);
                    nickname = cnt > 0 ? nickname + System.currentTimeMillis() : nickname;
                    String avatar = json2.getStr("figureurl_qq_2").replace("http://", "https://");
                    User qqRegisterUser = User.builder()
                            .role(RoleEnum.QQ_USER).createDate(new Date())
                            .nickname(nickname).avatar(avatar).openId(openId)
                            .enable(true)
                            .build();
                    boolean qqRegUser = userService.save(qqRegisterUser);
                    if (qqRegUser) {
                        userCoinRecordMapper.insert(
                                UserCoinRecord.builder().operateTime(new Date()).operateType(OperateType.INIT_REG)
                                        .operateValue(0).remainCoin(0).remark(OperateType.INIT_REG.getDesc())
                                        .userId(qqRegisterUser.getId()).build()
                        );
                        return ResultBean.ok("授权成功！", "/").put(NBV5.SESSION_USER_KEY, qqRegisterUser);
                    } else {
                        return ResultBean.error("QQ授权成功，注册到系统中失败，请稍候重试！");
                    }
                }
            } else {
                String errorMsg = json2.getStr("msg");
                log.error("QQ登录授权失败，原因：{}", errorMsg);
                return ResultBean.error("QQ登录授权失败，原因：{}", errorMsg);
            }
        } catch (StringIndexOutOfBoundsException e) {
            log.error("[accessToken] 返回值有误！");
            return ResultBean.error("请求重复或返回 [accessToken] 值有误！");
        } catch (Exception e) {
            log.error("QQ登录授权失败", e);
            return ResultBean.error("QQ登录授权失败，原因：{}", e.getMessage());
        }
    }
}
