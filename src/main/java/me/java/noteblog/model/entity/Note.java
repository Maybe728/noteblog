package me.java.noteblog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note implements Serializable {

    private Long id;
    @NotEmpty
    private String title;
    private String clearContent;
    private String mdContent;
    @NotEmpty
    private String content;
    @Builder.Default
    @TableField("`top`")
    private Boolean top = FALSE;
    @Builder.Default
    @TableField("`show`")
    private Boolean show = TRUE;
    @Builder.Default
    @TableField("`post`")
    private Date post = new Date();
    @TableField("`modify`")
    private Date modify;
}
