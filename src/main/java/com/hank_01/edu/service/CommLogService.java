package com.hank_01.edu.service;

import com.hank_01.edu.dto.CommLogDTO;

import java.util.Date;
import java.util.List;

public interface CommLogService {
    /**
     * 插入新的佣金分配日志
     * @param dto
     * @return Boolean
     */
    Boolean insertCommLog(CommLogDTO dto);


    /**
     * 通过id查找佣金日志信息
     * @param id
     * @return 佣金日志信息
     */
    CommLogDTO findById(Long id );

    /**
     * 根据条件查询佣金日志信息
     * @param agentPlayerId 代理商/代理玩家id
     * @param orderId 订单id
     * @param startTime 筛选开始时间
     * @param endTime 筛选结束时间
     * @return 佣金日志信息列表
     */
    List<CommLogDTO> findByCondition(Long agentPlayerId,Long orderId, Date startTime , Date endTime);
}
