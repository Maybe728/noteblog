package me.java.noteblog.model.bo;

import lombok.*;
import me.java.noteblog.model.entity.Article;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
public class SearchArticleBo extends Article {

    private String type;
    private String resContent;
}
