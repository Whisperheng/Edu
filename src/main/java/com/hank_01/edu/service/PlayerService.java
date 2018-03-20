package com.hank_01.edu.service;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.util.List;

public interface PlayerService {
    /**
     * 根据ID查找指定玩家信息
     * @param id
     * @return PlayerDTO
     */
    PlayerDTO findPlayerById(Long id );

    /**
     * 根据条件查询玩家列表
     * @param agentType 代理等级
     * @param onLineStatus 在线状态
     * @param playStatus 玩家账户状态
     * @return List<PlayerDTO>
     */
    List<PlayerDTO> findPlayersByCondition(AgentType agentType ,
                                           OnLineStatus onLineStatus ,
                                           PlayStatus playStatus);

    /**
     * 更改玩家信息，代理信息 状态信息除外
     * @param dto
     * @return boolean
     */
    Boolean updatePlayer(PlayerDTO dto);

    /**
     * 根据ID改变玩家代理等级
     * @param id 玩家id
     * @param newAgentType 新的代理等级
     * @return Boolean
     */
    Boolean updatePlayerAgentTypeById(Long id, AgentType newAgentType);
    /**
     * 根据ID改变玩家玩家状态
     * @param id 玩家id
     * @param newPlayStatus 新的玩家状态
     * @return Boolean
     */
    Boolean updatePlayerStatusById(Long id ,PlayStatus newPlayStatus);
}
