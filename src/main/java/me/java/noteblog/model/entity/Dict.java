package me.java.noteblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dict implements Serializable {

    private Long id;
    private String name;
    @TableField("`group`")
    private String group;
}
