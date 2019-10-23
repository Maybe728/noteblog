package me.java.noteblog.constant;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum RoleEnum implements IEnum<Integer> {
    /**
     * 用户的角色类别
     */
    ADMIN(1, "管理员"),
    REG_USER(2, "注册用户"),
    QQ_USER(3, "QQ登录用户"),
    GITHUB_USER(4, "GITHUB登录用户");


    private int code;
    private String descp;

    RoleEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @Override
    public Integer getValue() {
        return this.code;
    }

    public String getDescp() {
        return this.descp;
    }
}
