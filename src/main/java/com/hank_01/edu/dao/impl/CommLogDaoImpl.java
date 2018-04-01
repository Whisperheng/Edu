package com.hank_01.edu.dao.impl;

import com.hank_01.edu.Entity.CommLogEntity;
import com.hank_01.edu.dao.CommLogDao;
import com.hank_01.edu.enums.errorEnum.CommLogError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.mapper.CommLogMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class CommLogDaoImpl implements CommLogDao {

    @Autowired
    private CommLogMapper mapper;

    private static final Logger LOG = LoggerFactory.getLogger(CommLogDaoImpl.class);

    @Override
    public Boolean insertCommLog(CommLogEntity entity) {
        this.validateParameterForInsert(entity);
        entity.setId(new Date().getTime());
        return mapper.insert(entity);
    }

    @Override
    public CommLogEntity findById(Long id) {
        if (id == null){
            LOG.error("param error id is null");
            return null;
        }
        return mapper.findById(id);
    }

    @Override
    public List<CommLogEntity> findByCondition(Long agentPlayerId,Long orderId,  Date startTime, Date endTime) {

        return mapper.findByCondition(agentPlayerId,orderId , startTime,endTime);
    }

    private void validateParameterForInsert(CommLogEntity entity){
        if (entity == null){
            throw new EduException(CommLogError.PARAMETER_ERROR);
        }
        if (entity.getAgentPlayerId() == null){
            throw  new EduException(CommLogError.AGENT_PLAYER_ERROR);
        }
        if (entity.getOrderId() ==null){
            throw  new EduException(CommLogError.ORDER_NOT_EXISTED);
        }
        if (entity.getOrderGoldAmount() == null){
            throw  new EduException(CommLogError.ORDER_GOLD_COUNT_ERROR);
        }
        if (entity.getOrderPrice() == null){
            throw  new EduException(CommLogError.ORDER_PRICE_ERROR);
        }
        if (entity.getRewardAmount() == null){
            throw new EduException(CommLogError.REWARD_AMOUNT_ERROR);
        }
    }
}
