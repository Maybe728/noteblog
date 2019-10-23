package me.java.noteblog.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import me.java.noteblog.model.entity.Comment;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CommentBo extends Comment {

    private String articleId;
    private String nickname;
    private String avatar;
    private String title;
    private String role;
}
