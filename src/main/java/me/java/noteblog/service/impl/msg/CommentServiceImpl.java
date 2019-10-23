package me.java.noteblog.service.impl.msg;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.java.noteblog.service.interfaces.msg.CommentService;
import me.java.noteblog.mapper.CommentMapper;
import me.java.noteblog.model.bo.CommentBo;
import me.java.noteblog.model.bo.ReplyBo;
import me.java.noteblog.model.entity.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public IPage<CommentBo> findCommentPage(IPage<CommentBo> page,
                                            String nickname, String clearComment,
                                            List<String> articleIds, Long userId, boolean enable) {
        return commentMapper.findCommentPage(page, nickname, clearComment, articleIds, userId, enable);
    }

    @Override
    public CommentBo findLatestComment() {
        return commentMapper.findLatestComment();
    }

    @Override
    public long findTodayComment() {
        return commentMapper.findTodayComment();
    }

    @Override
    public IPage<ReplyBo> findReplyPage(IPage<ReplyBo> page, Long userId) {
        return commentMapper.findReplyPage(page, userId);
    }
}
