package me.java.noteblog.model.bo.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QqLoginData implements Serializable {

    private String callbackDomain;
    private String code;


}
