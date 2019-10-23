package me.java.noteblog.service.interfaces.msg;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import me.java.noteblog.model.bo.MessageBo;
import me.java.noteblog.model.entity.Message;

public interface MessageService extends IService<Message> {

    /**
     * 查找评论页面
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param enable
     * @return
     */
    IPage<MessageBo> findMessagePage(IPage<MessageBo> page, String nickname, String clearComment, boolean enable);

    /**
     * 查找最新的留言
     *
     * @return
     */
    MessageBo findLatestMessage();

    /**
     * 统计今日评论数量
     * @return
     */
    long findTodayMessage();
}
