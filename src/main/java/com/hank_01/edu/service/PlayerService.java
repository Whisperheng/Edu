package com.hank_01.edu.service;

import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.util.List;

public interface PlayerService {
    /**
     * 创建游戏玩家账号 ： 1，无代理推荐玩家  默认非代理 无上级代理
     *                  2，代理推荐玩家  检查上级代理是否存在 存入上级代理昵称
     * @param dto
     * @return
     */
    Boolean createPlayer(PlayerDTO dto);

    /**
     * 根据ID查找指定玩家信息
     * @param id
     * @return PlayerDTO
     */
    PlayerDTO findPlayerById(Long id );

    /**
     * 根据条件查询玩家列表
     * @param agentLever 代理等级
     * @param onLineStatus 在线状态
     * @param playStatus 玩家账户状态
     * @return List<PlayerDTO>
     */
    List<PlayerDTO> findPlayersByCondition(AgentLever agentLever,
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
     * 仅普通玩家可以更改，普通无上级代理玩家可申请成为商家之外的其他代理
     *                      有上级玩家需根据自己上级代理等级
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
