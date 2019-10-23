package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.bo.CommentBo;
import me.java.noteblog.model.bo.ReplyBo;
import me.java.noteblog.model.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询评论分页
     *
     * @param page
     * @param nickname
     * @param clearComment
     * @param articleIds
     * @param userId
     * @param enable
     * @return
     */
    IPage<CommentBo> findCommentPage(IPage<CommentBo> page,
                                     @Param("nickname") String nickname,
                                     @Param("clearComment") String clearComment,
                                     @Param("articleIds") List<String> articleIds,
                                     @Param("userId") Long userId,
                                     @Param("enable") boolean enable);

    /**
     * 查找最新的评论
     *
     * @return
     */
    CommentBo findLatestComment();

    /**
     * 统计今日评论数量
     *
     * @return
     */
    long findTodayComment();

    /**
     * 查找回复我的
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<ReplyBo> findReplyPage(IPage<ReplyBo> page, @Param("userId") Long userId);
}
