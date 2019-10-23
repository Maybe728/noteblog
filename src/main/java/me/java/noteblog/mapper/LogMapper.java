package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.bo.EchartsBo;
import me.java.noteblog.model.bo.EchartsUrlBo;
import me.java.noteblog.model.entity.Log;

import java.util.List;


@Mapper
public interface LogMapper extends BaseMapper<Log> {

    /**
     * 统计访客ip信息
     *
     * @return
     */
    List<EchartsBo> ipSummary();

    /**
     * url统计
     *
     * @return
     */
    List<EchartsUrlBo> urlSummary();

    /**
     * 浏览器统计
     *
     * @return
     */
    List<EchartsBo> browserSummary();

    /**
     * 今日访问
     *
     * @return
     */
    long todayVisit();

    /**
     * 删除30天之前的日志
     */
    void removeLogBeyondMonth();
}
