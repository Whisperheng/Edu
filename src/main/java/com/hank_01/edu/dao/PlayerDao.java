package com.hank_01.edu.dao;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.util.List;

public interface PlayerDao {

    /**
     * 创建新的玩家账号
     * @param entity
     * @return
     */
    Boolean createPlayer(PlayerEntity entity);

    /**
     * 根据ID查找指定玩家信息
     * @param id
     * @return PlayerEntity
     */
    PlayerEntity findPlayerById(Long id );

    /**
     * 根据条件查询玩家列表
     * @param agentLever 代理等级
     * @param onLineStatus 在线状态
     * @param playStatus 玩家账户状态
     * @return List<PlayerEntity>
     */
    List<PlayerEntity> findPlayersByCondition(AgentLever agentLever,
                                              OnLineStatus onLineStatus ,
                                              PlayStatus playStatus);

    /**
     * 更改玩家信息，代理信息 状态信息除外
     * @param entity
     * @return boolean
     */
    Boolean updatePlayer(PlayerEntity entity);

    /**
     * 根据ID改变玩家代理等级
     * @param id 玩家id
     * @param newAgentLever 新的代理等级
     * @return Boolean
     */
    Boolean updatePlayerAgentTypeById(Long id, AgentLever newAgentLever);
    /**
     * 根据ID改变玩家玩家状态
     * @param id 玩家id
     * @param newPlayStatus 新的玩家状态
     * @return Boolean
     */
    Boolean updatePlayerStatusById(Long id ,PlayStatus newPlayStatus);
}
