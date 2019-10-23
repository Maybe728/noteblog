package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.entity.Dict;

import java.util.List;


@Mapper
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 查询标签使用数到首页标签面板上显示
     *
     * @return
     */
    List<Dict> findTagsTab();
}
