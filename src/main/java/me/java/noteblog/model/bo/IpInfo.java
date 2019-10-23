package me.java.noteblog.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class IpInfo implements Serializable {

    /**
     * ip详细信息地址
     */
    private String address;

    /**
     * 运营商/网络线路
     */
    private String line;
}
