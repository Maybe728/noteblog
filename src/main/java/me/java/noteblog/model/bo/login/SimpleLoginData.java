package me.java.noteblog.model.bo.login;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

@Data
public class SimpleLoginData implements Serializable {

    private String nbv5name;
    private String nbv5pass;
    private String nbv5code;

}
