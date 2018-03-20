package com.hank_01.edu.dao.impl;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.mapper.PlayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PlayerDaoImpl implements PlayerDao {
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
        return null;
    }

    @Override
    public Boolean updatePlayer(PlayerEntity entity) {
        return null;
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
