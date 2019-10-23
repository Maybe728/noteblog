package me.java.noteblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Param implements Serializable {

    private Long id;
    private String name;
    private String value;
    @TableField("`group`")
    private String group;
    private String remark;
    private Integer orderIndex;
}
