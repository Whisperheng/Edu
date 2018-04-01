package com.hank_01.edu.dao;

import com.hank_01.edu.Entity.CommLogEntity;

import java.util.Date;
import java.util.List;

public interface CommLogDao {

    /**
     * 插入操作
     * @param entity
     * @return 操作执行结果 true/false
     */
    Boolean insertCommLog(CommLogEntity entity);

    /**
     * 通过id查找佣金日志信息
     * @param id
     * @return 佣金日志信息
     */
    CommLogEntity findById(Long id );

    /**
     * 根据条件查询佣金日志信息
     * @param agentPlayerId 代理商/代理玩家id
     * @param orderId 订单id
     * @param startTime 筛选开始时间
     * @param endTime 筛选结束时间
     * @return 佣金日志信息列表
     */
    List<CommLogEntity> findByCondition(Long agentPlayerId,Long orderId, Date startTime , Date endTime);
}
