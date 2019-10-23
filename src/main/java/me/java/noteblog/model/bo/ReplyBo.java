package me.java.noteblog.model.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class ReplyBo implements Serializable {

    private Long floorId;
    private String articleId;
    private Date replyTime;
    private String title;
    private String userDesc;
    private String replyContent;
}
