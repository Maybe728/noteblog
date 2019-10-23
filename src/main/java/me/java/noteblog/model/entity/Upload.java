package me.java.noteblog.model.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class Upload implements Serializable {

    private Long id;
    private String diskPath;
    private String virtualPath;
    private Date upload;
    private String type;
    private Long userId;
    private String articleId;
}
