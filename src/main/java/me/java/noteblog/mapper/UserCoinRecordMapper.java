package me.java.noteblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.java.noteblog.annotation.Mapper;
import me.java.noteblog.constant.OperateType;
import me.java.noteblog.model.entity.UserCoinRecord;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserCoinRecordMapper extends BaseMapper<UserCoinRecord> {

    /**
     * 找最新的一条
     *
     * @param userId
     * @return
     */
    UserCoinRecord findLatestRecordByUserId(@Param("userId") long userId);


    /**
     * 用户今日是否已签到，返回签到的记录数
     *
     * @param userId
     * @param operateType
     * @return
     */
    int todayIsSigned(@Param("userId") long userId, @Param("operateType") OperateType operateType);

}
