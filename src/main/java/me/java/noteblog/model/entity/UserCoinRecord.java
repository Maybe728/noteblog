package me.java.noteblog.model.entity;

import lombok.Builder;
import lombok.Data;
import me.java.noteblog.constant.OperateType;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class UserCoinRecord implements Serializable {

    private Long id;
    private Long userId;
    private OperateType operateType;
    private Date operateTime;
    private int operateValue;
    private int remainCoin;
    private String remark;


}
