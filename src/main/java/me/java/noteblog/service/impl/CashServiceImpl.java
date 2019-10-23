package me.java.noteblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.java.noteblog.service.interfaces.CashService;
import me.java.noteblog.mapper.CashMapper;
import me.java.noteblog.model.entity.Cash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CashServiceImpl extends ServiceImpl<CashMapper, Cash> implements CashService {
}
