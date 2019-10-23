package me.java.noteblog.service.interfaces.log;


import me.java.noteblog.model.bo.EchartsBo;
import me.java.noteblog.model.bo.EchartsUrlBo;

import java.util.List;

public interface LogService {

    /**
     * 统计ip
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
     * 今日访问量
     *
     * @return
     */
    long todayVisit();
}
