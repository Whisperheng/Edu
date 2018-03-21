package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.common.util.CollectionUtil;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao  playerDao;
    @Override
    public PlayerDTO findPlayerById(Long id) {
        if(id == null){
            return null;
        }
        PlayerEntity entity = playerDao.findPlayerById(id);
        if (entity == null){
            return null;
        }
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.convertFrom(entity);
        return playerDTO;
    }

    @Override
    public List<PlayerDTO> findPlayersByCondition(AgentType agentType, OnLineStatus onLineStatus, PlayStatus playStatus) {
        List<PlayerEntity> playerEntityList = playerDao.findPlayersByCondition(agentType,onLineStatus,playStatus);
        if (CollectionUtil.isEmpty(playerEntityList)){
            return null;
        }
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        for (PlayerEntity entity :playerEntityList){
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.convertFrom(entity);
            playerDTOList.add(playerDTO);
        }
        return playerDTOList;
    }

    @Override
    public Boolean updatePlayer(PlayerDTO dto) {
        if (dto == null || dto.getId() == null){
            return false;
        }
        return playerDao.updatePlayer(dto.convert2Entity());
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
