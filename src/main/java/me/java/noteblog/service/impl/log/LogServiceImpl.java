package me.java.noteblog.service.impl.log;

import me.java.noteblog.service.interfaces.log.LogService;
import me.java.noteblog.mapper.LogMapper;
import me.java.noteblog.model.bo.EchartsBo;
import me.java.noteblog.model.bo.EchartsUrlBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class LogServiceImpl implements LogService {

    private final LogMapper logMapper;

    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public List<EchartsBo> ipSummary() {
        return logMapper.ipSummary();
    }

    @Override
    public List<EchartsUrlBo> urlSummary() {
        return logMapper.urlSummary();
    }

    @Override
    public List<EchartsBo> browserSummary() {
        return logMapper.browserSummary();
    }

    @Override
    public long todayVisit() {
        return logMapper.todayVisit();
    }
}
