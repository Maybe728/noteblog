package me.java.noteblog.service.interfaces.mail;

import me.java.noteblog.model.entity.Article;

public interface MailService {


    /**
     * 发送邮件
     *
     * @param subject    邮件主题
     * @param targetMail 发送给谁，目标邮件
     * @param content    邮件内容
     * @param isHtml     是否发送富文本
     * @return
     */
    void sendMail(String subject, String targetMail, String content, boolean isHtml);

    /**
     * 发送评论通知邮件
     *
     * @param site
     * @param article
     * @param comment
     */
    void sendNoticeMail(String site, Article article, String comment);

    /**
     * 发送留言通知
     *
     * @param site
     * @param message
     */
    void sendMessageMail(String site, String message);

    /**
     * 发送注册验证邮件
     *
     * @param email
     */
    void sendMailCode(String email);
}
