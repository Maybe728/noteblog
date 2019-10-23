package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.entity.Param;

@Mapper
public interface ParamMapper extends BaseMapper<Param> {

    /**
     * 更新参数
     *
     * @param name
     * @param value
     */
    void updateValueByName(String name, Object value);

}
