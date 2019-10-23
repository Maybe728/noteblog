package me.java.noteblog.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.java.noteblog.constant.OperateType;
import me.java.noteblog.constant.RoleEnum;
import me.java.noteblog.service.interfaces.UserService;
import me.java.noteblog.mapper.UserCoinRecordMapper;
import me.java.noteblog.mapper.UserMapper;
import me.java.noteblog.model.entity.User;
import me.java.noteblog.model.entity.UserCoinRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;
    private final UserCoinRecordMapper userCoinRecordMapper;

    public UserServiceImpl(UserMapper userMapper, UserCoinRecordMapper userCoinRecordMapper) {
        this.userMapper = userMapper;
        this.userCoinRecordMapper = userCoinRecordMapper;
    }

    @Override
    public User findByQqOpenId(String openId, Boolean enable) {
        return super.getOne(Wrappers.<User>query()
                .eq("open_id", openId)
                .eq("role", RoleEnum.QQ_USER.getValue())
                .eq("enable", enable), true);
    }

    @Override
    public int countNickname(String nickname) {
        return super.count(Wrappers.<User>query().eq("nickname", nickname));
    }

    @Override
    public User findByGithub(String username, Boolean enable) {
        return super.getOne(Wrappers.<User>query()
                .eq("username", username)
                .eq("role", RoleEnum.GITHUB_USER.getValue())
                .eq("enable", enable), true);
    }

    @Override
    public long findTodayUser() {
        return userMapper.findTodayUser();
    }

    @Override
    public int countEmailAndUsername(String email, String username) {
        return userMapper.countRegUserEmailAndUsername(email, username);
    }

    @Override
    public int userRegister(String username, String password, String email, String nickname) {
        User regUser = User.builder()
                .avatar("/static/assets/img/noavatar.png")
                .nickname(nickname)
                .createDate(new Date())
                .enable(true)
                .username(username)
                .email(email)
                .password(SecureUtil.md5(password))
                .role(RoleEnum.REG_USER)
                .build();
        int res = userMapper.insert(regUser);
        if (res == 1) {
            userCoinRecordMapper.insert(
                    UserCoinRecord.builder().operateTime(new Date()).operateType(OperateType.INIT_REG)
                            .operateValue(0).remainCoin(0).remark(OperateType.INIT_REG.getDesc())
                            .userId(regUser.getId()).build()
            );
        }
        return res;
    }


}
