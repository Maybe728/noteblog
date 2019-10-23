package me.java.noteblog.service.interfaces;

public interface LoginService<R, P> {

    /**
     * 登录
     *
     * @param param
     * @return
     */
    R doLogin(P param);
}
