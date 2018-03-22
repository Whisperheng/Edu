package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.common.util.CollectionUtil;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.enums.errorEnum.PlayerError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerDao  playerDao;

    @Override
    public Boolean createPlayer(PlayerDTO dto) {

        if (dto.getSuperLeverCount() != null){
            PlayerDTO  superLever = this.findPlayerById(dto.getSuperLeverCount());
            if (superLever == null){
                throw new EduException(PlayerError.SUPER_LEVER_COUNT_IS_INVALID);
            }
            dto.setSuperLeverName(superLever.getNickName());
        }

        return playerDao.createPlayer(dto.convert2Entity());
    }

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
    public List<PlayerDTO> findPlayersByCondition(AgentLever agentLever, OnLineStatus onLineStatus, PlayStatus playStatus) {
        List<PlayerEntity> playerEntityList = playerDao.findPlayersByCondition(agentLever,onLineStatus,playStatus);
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
    public Boolean updatePlayerAgentTypeById(Long id, AgentLever newAgentLever) {
        if (id == null ){
            LOG.info("更改代理等级失败 ：参数错误，更新玩家代理状态时ID为空 。");
            return false;
        }
        if (AgentLever.LEVER_SUPER == newAgentLever ){
            LOG.info("更改代理等级失败 ：未经许可，不能成为商家代理 ");
            return false;
        }
        PlayerDTO playerDTO = this.findPlayerById(id);
        if (playerDTO == null){
            throw new EduException(PlayerError.PLAYER_DOES_NOT_EXISTED);
        }
        if (playerDTO.getAgentLever() != AgentLever.LEVER_NULL){
            throw new EduException(PlayerError.CAN_NOT_CHANGE_AGENT_LEVER);
        }
        if (playerDTO.getSuperLeverCount() != null){
            newAgentLever = this.getRightAgentLever(playerDTO.getAgentLever());
        }
        return playerDao.updatePlayerAgentTypeById(id, newAgentLever);
    }

    /**
     * 根据自己的上级代理来确定自己的可申请的代理 级别
     * @param superAgentLever
     * @return AgentLever
     */
    private AgentLever getRightAgentLever(AgentLever superAgentLever){
        if (superAgentLever == null){
            return null;
        }
        switch (superAgentLever){
            case LEVER_NULL:
                return null;
            case LEVER_1:
                return AgentLever.LEVER_2;
            case LEVER_2:
                return AgentLever.LEVER_3;
            case LEVER_3:
                return null;
            case LEVER_SUPER:
                return AgentLever.LEVER_1;
            default:
                return null;
        }
    }

    @Override
    public Boolean updatePlayerStatusById(Long id, PlayStatus newPlayStatus) {
        return null;
    }
}
