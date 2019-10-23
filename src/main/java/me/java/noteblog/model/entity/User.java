package me.java.noteblog.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.java.noteblog.constant.RoleEnum;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private Long id;
    private RoleEnum role;
    private String avatar;
    private Date createDate;
    private String email;
    @Builder.Default
    private Boolean enable = true;
    private String nickname;
    private String password;
    private String openId;
    private String username;
    private Integer remainCoin;
}
