package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.CommLogEntity;
import com.hank_01.edu.common.util.CollectionUtil;
import com.hank_01.edu.dao.CommLogDao;
import com.hank_01.edu.dto.CommLogDTO;
import com.hank_01.edu.enums.errorEnum.CommLogError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.service.CommLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommLogServiceImpl implements CommLogService {

    private static final Logger LOG = LoggerFactory.getLogger(CommLogServiceImpl.class);
    @Autowired
    private CommLogDao commLogDao;

    @Override
    public Boolean insertCommLog(CommLogDTO dto) {
        this.validateParam(dto);
        LOG.debug("参数验证通过！，即将进行插入操作...");
        return commLogDao.insertCommLog(dto.convert2Entity());
    }

    @Override
    public CommLogDTO findById(Long id) {
        if (id == null){
            LOG.error("查找失败 ，id为空");
            return null;
        }
        CommLogEntity entity = commLogDao.findById(id);
        if (entity == null){
            LOG.debug("未查找到结果！");
            return null;
        }
        LOG.debug("查找结果： entity  " + entity.toString());
        CommLogDTO dto = new CommLogDTO();
        dto.convertFromEntity(entity);
        return dto;
    }

    @Override
    public List<CommLogDTO> findByCondition(Long agentPlayerId ,Long orderId , Date startTime, Date endTime) {
        List<CommLogEntity> entityList = commLogDao.findByCondition(agentPlayerId ,orderId,startTime,endTime);
        if (CollectionUtil.isEmpty(entityList)){
            LOG.debug("未查询到结果！");
            return null;
        }
        List<CommLogDTO> dtoList = new ArrayList<>();
        for (CommLogEntity entity :entityList){
            CommLogDTO dto = new CommLogDTO();
            dto.convertFromEntity(entity);
            dtoList.add(dto);
        }
        return dtoList;
    }
    private void validateParam(CommLogDTO dto){
        if (dto == null){
            throw new EduException(CommLogError.PARAMETER_ERROR);
        }
        if (dto.getAgentPlayerId() == null){
            throw new EduException(CommLogError.AGENT_PLAYER_ERROR);
        }
        if (dto.getOrderGoldAmount() == null){
            throw new EduException(CommLogError.ORDER_GOLD_COUNT_ERROR);
        }
        if (dto.getOrderPrice() == null){
            throw new EduException(CommLogError.ORDER_PRICE_ERROR);
        }
        if (dto.getOrderId() == null){
            throw new EduException(CommLogError.ORDER_NOT_EXISTED);
        }
    }
}
