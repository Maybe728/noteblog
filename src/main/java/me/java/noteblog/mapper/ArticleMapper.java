package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.entity.Article;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 处理改动 所有 top 对应值
     *
     * @param currentTop
     */
    void updateTopsByTop(int currentTop);

    /**
     * 查找最大的top
     *
     * @return
     */
    int selectMaxTop();

    /**
     * 统计文章总字数
     *
     * @return
     */
    long sumArticleWords();

    /**
     * 随机n篇文章
     *
     * @param limit
     * @return
     */
    List<Article> findRandomArticles(int limit);

    /**
     * 更新浏览量
     *
     * @param articleId
     */
    void updateViewsById(String articleId);

    /**
     * 更新文章点赞数
     *
     * @param articleId
     * @return
     */
    int updateApproveCntById(String articleId);
}
