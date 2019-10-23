package me.java.noteblog.service.interfaces.content;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.java.noteblog.model.bo.SearchArticleBo;
import me.java.noteblog.model.bo.SearchBo;

public interface SearchService {

    /**
     * 查找搜索内容，包括文章和笔记
     *
     * @param words
     * @param pageNo
     * @return
     */
    Page<SearchBo> searchWithWords(String words, int pageNo);

    /**
     * 查找搜索内容
     *
     * @param tagName
     * @param pageNo
     * @param dictGroup
     * @return
     */
    Page<SearchArticleBo> searchWithDict(String dictGroup, String tagName, int pageNo);
}
