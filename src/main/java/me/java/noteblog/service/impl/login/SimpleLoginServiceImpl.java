package me.java.noteblog.service.impl.login;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import me.java.noteblog.constant.NBV5;
import me.java.noteblog.constant.RoleEnum;
import me.java.noteblog.service.interfaces.LoginService;
import me.java.noteblog.service.interfaces.UserService;
import me.java.noteblog.model.ResultBean;
import me.java.noteblog.model.bo.login.SimpleLoginData;
import me.java.noteblog.model.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 普通登录方法
 */
@Service("simpleLogin")
@Transactional(rollbackFor = Exception.class)
public class SimpleLoginServiceImpl implements LoginService<ResultBean, SimpleLoginData> {

    private final UserService userService;

    public SimpleLoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultBean doLogin(SimpleLoginData data) {
        User user = userService.getOne(Wrappers.<User>query()
                .eq("username", data.getNbv5name())
                .eq("password", data.getNbv5pass())
                .eq("enable", true)
        );
        if (user != null) {
            String redirectUrl =
                    user.getRole() == RoleEnum.ADMIN
                            ? NBV5.MANAGEMENT_INDEX
                            : NBV5.FRONTEND_INDEX;
            return ResultBean.ok("登陆成功！", redirectUrl).put("nbv5su", user);
        } else {
            return ResultBean.error("用户名或密码错误！");
        }

    }


}
