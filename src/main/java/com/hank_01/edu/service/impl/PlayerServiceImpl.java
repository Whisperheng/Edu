package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.common.util.CollectionUtil;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.dto.PlayerSummaryDTO;
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
    private PlayerDao playerDao;

    @Override
    public Boolean createPlayer(PlayerDTO dto) {

        if (dto.getSuperLeverCount() != null){
            PlayerDTO superLever = this.findPlayerById(dto.getSuperLeverCount());
            if (superLever == null){
                throw new EduException(PlayerError.SUPER_LEVER_COUNT_IS_INVALID);
            }
            dto.setSuperLeverName(superLever.getNickName());
            dto.setSuperAgentLever(superLever.getAgentLever());
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

        Boolean qualificationResult = this.agentQualification(id,newAgentLever);
        if (! qualificationResult){
            throw new EduException(PlayerError.QUALIFICATION_AGENT_FAIL);
        }

        Long superLeverCount = this.getRightSuperCount(newAgentLever);
        PlayerDTO superAgent = this.findPlayerById(superLeverCount);
        return playerDao.updatePlayerAgentTypeById(id, newAgentLever ,superLeverCount ,superAgent.getNickName(),superAgent.getAgentLever());
    }

    private Long getRightSuperCount(AgentLever selfAgentLever){
        switch (selfAgentLever){
            case LEVER_1:
                return 1L;
            case LEVER_2:
                return 2L;
            case LEVER_3:
                return 3L;
            default:
                return null;
        }
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

    @Override
    public List<AgentLever> findAvailableAgent(Long id) {

        if (id == null){
           throw new EduException(PlayerError.PARAMETER_ERROR);
        }

        List<AgentLever> agentLeverList = new ArrayList<>();
        PlayerDTO dto = this.findPlayerById(id);
        if (dto == null){
           throw new EduException(PlayerError.PLAYER_DOES_NOT_EXISTED);
        }
        if (dto.getSuperLeverCount() == null){
            agentLeverList.add(AgentLever.LEVER_1);
            agentLeverList.add(AgentLever.LEVER_2);
            agentLeverList.add(AgentLever.LEVER_3);
            return agentLeverList;
        }
        PlayerDTO superPlayer = this.findPlayerById(id);
        if (superPlayer == null ){
            agentLeverList.add(AgentLever.LEVER_1);
            agentLeverList.add(AgentLever.LEVER_2);
            agentLeverList.add(AgentLever.LEVER_3);
            LOG.debug("玩家上级账户不存在，将玩家上级设为空");
            return agentLeverList;
        }
        AgentLever agentLever = this.getRightAgentLever(superPlayer.getAgentLever());
        agentLeverList.add(agentLever);
        return agentLeverList;

    }

    @Override
    public PlayerSummaryDTO findPlayerSummary() {

        PlayerSummaryDTO summaryDTO = new PlayerSummaryDTO();
        List<PlayerEntity> playerEntityList1 = playerDao.findPlayersByCondition(AgentLever.LEVER_NULL,OnLineStatus.ONLINE,null);
        summaryDTO.setOnLinePlayerCount((long)playerEntityList1.size());
        List<PlayerEntity> playerEntityList2 = playerDao.findPlayersByCondition(null,null,null);
        //  商家代理不是玩家
        summaryDTO.setPlayerCount((long) (playerEntityList2.size()-1));
        List<PlayerEntity> playerEntityList3 = playerDao.findPlayersByCondition(AgentLever.LEVER_1,null,null);
        summaryDTO.setAgent_lever1Count((long)playerEntityList3.size());
        List<PlayerEntity> playerEntityList4 = playerDao.findPlayersByCondition(AgentLever.LEVER_2,null,null);
        summaryDTO.setAgent_lever2Count((long)playerEntityList4.size());
        List<PlayerEntity> playerEntityList5 = playerDao.findPlayersByCondition(AgentLever.LEVER_3,null,null);
        summaryDTO.setAgent_lever3Count((long)playerEntityList5.size());
        return summaryDTO;
    }

    /**
     * 验证是否具有成为所申请代理的资格
     * 一级代理 ：700 金币 ， 二级代理 ： 500金币 ， 三级代理 ： 300金币
     * @param id
     * @param newAgentLever
     * @return Boolean
     */
    private Boolean agentQualification(Long id, AgentLever newAgentLever) {
        if (id == null  || newAgentLever == null){
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
        switch (newAgentLever){
            case LEVER_1:
                return playerDTO.getGoldCount().intValue() >= 700;
            case LEVER_2:
                return playerDTO.getGoldCount().intValue() >= 500;
            case LEVER_3:
                return playerDTO.getGoldCount().intValue() >= 300;
            default:
                return false;
        }
    }
}
