package me.java.noteblog.controller.frontend;


import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import com.vdurmont.emoji.EmojiParser;
import me.java.noteblog.constant.DictGroup;
import me.java.noteblog.constant.NBV5;
import me.java.noteblog.controller.common.BaseController;
import me.java.noteblog.model.ResultBean;
import me.java.noteblog.model.entity.Article;
import me.java.noteblog.model.entity.Comment;
import me.java.noteblog.model.entity.Dict;
import me.java.noteblog.model.entity.Param;
import me.java.noteblog.service.interfaces.content.ArticleService;
import me.java.noteblog.service.interfaces.dict.DictService;
import me.java.noteblog.service.interfaces.mail.MailService;
import me.java.noteblog.service.interfaces.msg.CommentService;
import me.java.noteblog.service.interfaces.property.ParamService;
import me.java.noteblog.util.CacheUtils;
import me.java.noteblog.util.NbUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/token/comment")
public class CommentController extends BaseController {

    private final ParamService paramService;
    private final ArticleService articleService;
    private final DictService dictService;
    private final CommentService commentService;
    private final MailService mailService;

    public CommentController(ParamService paramService,
                             ArticleService articleService, DictService dictService,
                             CommentService commentService, MailService mailService) {
        this.paramService = paramService;
        this.articleService = articleService;
        this.dictService = dictService;
        this.commentService = commentService;
        this.mailService = mailService;
    }

    @PostMapping("/sub")
    @ResponseBody
    public ResultBean sub(@Valid Comment comment, BindingResult bindingResult, HttpServletRequest request) {
        Param globalCommentOnOff = paramService.findByName("global_comment_onoff");
        Article article = articleService.getById(comment.getArticleId());
        if ("1".equals(globalCommentOnOff.getValue()) && article.getCommented()) {
            if (!bindingResult.hasErrors()) {
                comment.setIpAddr(NbUtils.getRemoteAddress(request));
                boolean develop = NbUtils.getBean(Environment.class).getProperty("app.develop", Boolean.class, true);
                if (!develop) {
                    comment.setIpInfo(NbUtils.getIpInfo(comment.getIpAddr()).getAddress());
                } else {
                    comment.setIpInfo("本地/未知");
                }
                comment.setUserAgent(request.getHeader("user-agent"));
                comment.setComment(
                        HtmlUtil.removeHtmlTag(NbUtils.stripSqlXSS(comment.getComment()),
                                false, "style", "link", "meta", "script"));
                comment.setComment(EmojiParser.parseToHtmlDecimal(comment.getComment()));
                comment.setPost(LocalDateTime.now());
                comment.setClearComment(HtmlUtil.cleanHtmlTag(comment.getComment()));
                List<Dict> keywords = dictService.findList(DictGroup.GROUP_KEYWORD);
                keywords.forEach(
                        kw -> comment.setComment(comment.getComment().replace(kw.getName(), StrUtil.repeat("*", kw.getName().length()))));
                if (StringUtils.isEmpty(comment.getComment())) {
                    return ResultBean.error("评论正确填写评论内容！");
                }
                if (commentService.save(comment)) {
                    if ("1".equals(paramService.findByName(NBV5.COMMENT_MAIL_NOTICE_ONOFF).getValue())) {
                        mailService.sendNoticeMail(basePath(request), articleService.getById(comment.getArticleId()), comment.getComment());
                    }
                    CacheUtils.removeDefaultCache("commentCount");
                    return ResultBean.ok("发表评论成功");
                } else {
                    return ResultBean.error("发表评论失败");
                }
            } else {
                return ajaxJsr303(bindingResult.getFieldErrors());
            }
        } else {
            return ResultBean.error("未开放评论！");
        }
    }
}
