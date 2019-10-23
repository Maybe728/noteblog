package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.bo.MessageBo;
import me.java.noteblog.model.entity.Message;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询评论分页
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param enable
     * @return
     */
    IPage<MessageBo> findMessagePage(IPage<MessageBo> page,
                                     @Param("nickname") String nickname,
                                     @Param("clearComment") String clearComment,
                                     @Param("enable") boolean enable);

    /**
     * 查找最新的留言
     *
     * @return
     */
    MessageBo findLatestMessage();

    /**
     * 统计今日留言数量
     *
     * @return
     */
    long findTodayMessage();
}
