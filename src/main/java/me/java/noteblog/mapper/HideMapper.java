package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.model.bo.HideBo;
import me.java.noteblog.model.entity.Hide;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface HideMapper extends BaseMapper<Hide> {

    /**
     * 查找用户购买的主题
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<HideBo> findMyPurchases(IPage<HideBo> page, @Param("userId") Long userId);
}
