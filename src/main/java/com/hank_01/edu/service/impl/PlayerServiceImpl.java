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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerDao playerDao;

    @Override
    public Boolean createPlayer(PlayerDTO dto) {

        dto.setSuperLeverCount(1L);
        dto.setSuperLeverName("商家");
        if (dto.getSuperLeverCount() != null){
            PlayerDTO superLever = this.findPlayerById(dto.getSuperLeverCount());
            dto.setSuperLeverCount(null);
            dto.setSuperLeverName(null);
            if (superLever != null ){
                dto.setSuperLeverCount(superLever.getId());
                dto.setSuperLeverName(superLever.getNickName());
            }
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
    public List<PlayerDTO> findPlayersByCondition(Boolean agentFlag, OnLineStatus onLineStatus, PlayStatus playStatus) {
        List<PlayerEntity> playerEntityList = playerDao.findPlayersByCondition(agentFlag,onLineStatus,playStatus);
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
    public Boolean updatePlayerAgentTypeById(Long id ,Long superLeverCount) {
        if (id == null ){
            LOG.error("加入代理失败 ：参数错误，更新玩家代理状态时ID为空 。");
            return false;
        }
        if (superLeverCount == null){
            LOG.error("加入代理失败 ：参数错误，更新玩家代理状态时上级代理账户为空 。");
            return false;
        }
        PlayerDTO superAgent = this.findPlayerById(superLeverCount);
        if (superAgent == null){
            LOG.error("加入代理失败 ：上级代理账户无效。");
            return false;
        }

        PlayerDTO playerDTO = this.findPlayerById(id);
        if (playerDTO == null){
            throw new EduException(PlayerError.PLAYER_DOES_NOT_EXISTED);
        }
        Boolean qualificationResult = this.agentQualification(id);
        if (!qualificationResult){
            throw new EduException(PlayerError.QUALIFICATION_AGENT_FAIL);
        }
        return playerDao.updatePlayerAgentTypeById(id, true ,superLeverCount ,superAgent.getNickName());
    }

    @Override
    public Boolean updatePlayerStatusById(Long id, PlayStatus newPlayStatus) {
        return null;
    }



    @Override
    public PlayerSummaryDTO findPlayerSummary() {

        PlayerSummaryDTO summaryDTO = new PlayerSummaryDTO();
        List<PlayerEntity> playerEntityList1 = playerDao.findPlayersByCondition(false,OnLineStatus.ONLINE,null);
        if (CollectionUtil.isNotEmpty(playerEntityList1)) {
            summaryDTO.setOnLinePlayerCount((long)playerEntityList1.size());
        }
        List<PlayerEntity> playerEntityList2 = playerDao.findPlayersByCondition(null,null,null);
        if (CollectionUtil.isNotEmpty(playerEntityList2)){
            summaryDTO.setPlayerCount((long)playerEntityList2.size());
        }
        List<PlayerEntity> agentList = playerDao.findPlayersByCondition(true,null,null);
        if (CollectionUtil.isNotEmpty(agentList)){
            summaryDTO.setAgentCount((long)agentList.size());
        }
        return summaryDTO;
    }

    /**
     * 验证是否具有成为所申请代理的资格
     * @param id
     * @return Boolean
     */
    public Boolean agentQualification(Long id) {
        if (id == null ){
            return false;
        }
        PlayerDTO playerDTO = this.findPlayerById(id);
        if (playerDTO == null){
            throw new EduException(PlayerError.PLAYER_DOES_NOT_EXISTED);
        }
        if (playerDTO.getGoldCount().compareTo(new BigDecimal(300L))<0){
            throw new EduException(PlayerError.GOLD_COUNT_IS_NOT_ENOUGH);
        }
        return true;
    }
}
