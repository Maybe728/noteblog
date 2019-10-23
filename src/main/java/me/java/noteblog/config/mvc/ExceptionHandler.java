package me.java.noteblog.config.mvc;

import me.java.noteblog.exception.AppRunningException;
import me.java.noteblog.exception.AppSetException;
import me.java.noteblog.model.ResultBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            AppRunningException.class,
            AppSetException.class
    })
    @ResponseBody
    public ResultBean handle(Exception e) {
        return ResultBean.error(e.getMessage());
    }
}
