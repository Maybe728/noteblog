package me.java.noteblog.service.impl.msg;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.java.noteblog.service.interfaces.msg.MessageService;
import me.java.noteblog.mapper.MessageMapper;
import me.java.noteblog.model.bo.MessageBo;
import me.java.noteblog.model.entity.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public IPage<MessageBo> findMessagePage(IPage<MessageBo> page, String nickname, String clearComment, boolean enable) {
        return messageMapper.findMessagePage(page, nickname, clearComment, enable);
    }

    @Override
    public MessageBo findLatestMessage() {
        return messageMapper.findLatestMessage();
    }

    @Override
    public long findTodayMessage() {
        return messageMapper.findTodayMessage();
    }
}
