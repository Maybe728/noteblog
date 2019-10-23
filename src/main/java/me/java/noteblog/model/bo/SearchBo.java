package me.java.noteblog.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class SearchBo implements Serializable {

    private String id;
    /**
     * 文章还是笔记
     */
    private String type;
    private String title;
    private String resContent;
    private Date post;
}
