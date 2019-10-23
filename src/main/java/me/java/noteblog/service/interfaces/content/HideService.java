package me.java.noteblog.service.interfaces.content;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import me.java.noteblog.model.bo.HideBo;
import me.java.noteblog.model.entity.Hide;
import org.apache.ibatis.annotations.Param;

public interface HideService extends IService<Hide> {

    /**
     * 删除文章相关的关联
     *
     * @param articleId
     */
    void deleteArticlePurchaseRefer(String articleId);

    /**
     * 查询用户是否已经购买了
     *
     * @param articleId
     * @param userId
     * @param hideId
     * @return
     */
    boolean userIsBought(String articleId, long userId, String hideId);

    /**
     * 购买文章隐藏内容
     *
     * @param articleId
     * @param hideId
     * @param userId
     * @return
     */
    int purchaseArticleHideContent(String articleId, String hideId, Long userId);

    /**
     * 查找用户购买的主题
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<HideBo> findMyPurchases(IPage<HideBo> page, @Param("userId") Long userId);
}
