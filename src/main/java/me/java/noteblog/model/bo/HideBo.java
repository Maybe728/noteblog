package me.java.noteblog.model.bo;

import lombok.Getter;
import lombok.Setter;
import me.java.noteblog.model.entity.Hide;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class HideBo extends Hide implements Serializable {

    private String title;
    private Date purchaseTime;


    public HideBo(String id, String articleId, String hideType, String hideHtml, Integer hidePrice) {
        super(id, articleId, hideType, hideHtml, hidePrice);
    }
}
