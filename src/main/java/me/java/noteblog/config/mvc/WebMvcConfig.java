package me.java.noteblog.config.mvc;

import me.java.noteblog.constant.NBV5;
import me.java.noteblog.config.filter.AdminFilter;
import me.java.noteblog.config.filter.InitFilter;
import me.java.noteblog.config.filter.SessionFilter;
import me.java.noteblog.config.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final Environment env;

    @Autowired
    public WebMvcConfig(Environment env) {
        this.env = env;
    }

    /**
     * 添加一些虚拟路径的映射
     * 静态资源路径和上传文件的路径
     * 如果配置了七牛云上传，则上传路径无效
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(env.getProperty("spring.resources.static-locations"));
        String uploadPath = env.getProperty(NBV5.APP_UPLOAD_PATH);
        registry.addResourceHandler("/upfiles/**").addResourceLocations(uploadPath);
    }

    /**
     * 全局拦截器
     * 顺序：系统初始化/访问日志-->用户是否登录-->后台管理用户验证-->视图主题渲染
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePaths = Arrays.asList("/static/**", "/error/**", "/init/**");
        registry.addInterceptor(new InitFilter()).addPathPatterns("/**").excludePathPatterns(excludePaths);
        registry.addInterceptor(new SessionFilter()).addPathPatterns("/**").excludePathPatterns(excludePaths);
        registry.addInterceptor(new TokenFilter()).addPathPatterns("/token/**", "/**/token/**");
        registry.addInterceptor(new AdminFilter()).addPathPatterns("/management/**");
    }
}
