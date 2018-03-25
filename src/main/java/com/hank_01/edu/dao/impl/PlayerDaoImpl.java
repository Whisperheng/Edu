package com.hank_01.edu.dao.impl;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.dao.PlayerDao;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.enums.errorEnum.PlayerError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.mapper.PlayerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class PlayerDaoImpl implements PlayerDao {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerDaoImpl.class);

    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public Boolean createPlayer(PlayerEntity entity) {
        if (entity == null || entity.getWeChatId() == null
                           || entity.getNickName() ==null){
            throw new EduException(PlayerError.PARAMETER_IS_NOT_ENOUGH);
        }
        entity.setId(new Date().getTime());
        return playerMapper.insertPlayer(entity);
    }

    @Override
    public PlayerEntity findPlayerById(Long id) {
        if (id == null){
            return null;
        }
        return playerMapper.findById(id);
    }

    @Override
    public List<PlayerEntity> findPlayersByCondition(AgentLever agentLever, OnLineStatus onLineStatus, PlayStatus playStatus) {
        String agentTypeName = null;
        String onLineStatusName = null;
        String playerStatusName = null;
        if (agentLever != null){
            agentTypeName = agentLever.getName();
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
    public Boolean updatePlayerAgentTypeById(Long id, AgentLever newAgentLever ,Long superLeverCount ,String superLeverName ,AgentLever superAgentLever) {
        if (id == null || newAgentLever == null ){
            LOG.info("玩家代理等级更新失败，新代理等级为空或ID为空");
            return false;
        }
        PlayerEntity entity = this.findPlayerById(id);
        if (entity ==null){
            throw new EduException(PlayerError.PLAYER_DOES_NOT_EXISTED);
        }
        if (entity.getAgentLever() != AgentLever.LEVER_NULL){
            throw new EduException(PlayerError.CAN_NOT_CHANGE_AGENT_LEVER);
        }
        return playerMapper.updatePlayerAgentTypeById(id, newAgentLever ,superLeverCount,superLeverName,superAgentLever);
    }

    @Override
    public Boolean updatePlayerStatusById(Long id, PlayStatus newPlayStatus) {
        return null;
    }
}
