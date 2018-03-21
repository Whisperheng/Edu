package com.hank_01.edu.dao.impl;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.mapper.PlayerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PlayerDaoImpl implements PlayerDao {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerDaoImpl.class);

    @Autowired
    private PlayerMapper playerMapper;
    @Override
    public PlayerEntity findPlayerById(Long id) {
        if (id == null){
            return null;
        }
        return playerMapper.findById(id);
    }

    @Override
    public List<PlayerEntity> findPlayersByCondition(AgentType agentType, OnLineStatus onLineStatus, PlayStatus playStatus) {
        String agentTypeName = null;
        String onLineStatusName = null;
        String playerStatusName = null;
        if (agentType != null){
            agentTypeName = agentType.getName();
        }
        if (onLineStatus != null){
            onLineStatusName = onLineStatus.getName();
        }
        if (playStatus != null){
            playerStatusName = playStatus.getName();
        }
        return playerMapper.findPlayersByCondition(agentTypeName,onLineStatusName,playerStatusName);
    }

    @Override
    public Boolean updatePlayer(PlayerEntity entity) {
        if (entity == null || entity.getId() == null){
            LOG.info("玩家信息更新失败，参数为空或ID为空");
            return false;
        }
        return playerMapper.updatePlayer(entity);
    }

    @Override
    public Boolean updatePlayerAgentTypeById(Long id, AgentType newAgentType) {
        return null;
    }

    @Override
    public Boolean updatePlayerStatusById(Long id, PlayStatus newPlayStatus) {
        return null;
    }
}
