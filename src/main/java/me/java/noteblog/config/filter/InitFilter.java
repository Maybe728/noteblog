package me.java.noteblog.config.filter;

import me.java.noteblog.service.interfaces.property.ParamService;
import me.java.noteblog.util.NbUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于判断是否已经安装好了笔记博客系统的过滤作用
 */
public class InitFilter extends HandlerInterceptorAdapter {

    private static final String INIT_SUCCESS = "1";
    private static final String INIT_URL = "/init";
    private ParamService paramService = NbUtils.getBean(ParamService.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (NbUtils.noteBlogIsInstalled()) {
            return true;
        } else {
            response.sendRedirect(INIT_URL);
            return false;
        }
    }
}
