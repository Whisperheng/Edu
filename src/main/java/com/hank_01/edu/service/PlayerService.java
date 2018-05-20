package com.hank_01.edu.service;

import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.dto.PlayerSummaryDTO;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.util.List;

public interface PlayerService {
    /**
     * 创建游戏玩家账号 ： 1，无代理推荐玩家  默认非代理 无上级代理
     *                  2，代理推荐玩家  检查上级代理是否存在 存入上级代理昵称
     * @param dto 玩家信息DTO
     * @return Boolean 创建结果
     */
    Boolean createPlayer(PlayerDTO dto);

    Boolean agentQualification(Long id );

    /**
     * 根据ID查找指定玩家信息
     * @param id 玩家id
     * @return PlayerDTO 玩家信息DTO
     */
    PlayerDTO findPlayerById(Long id );

    /**
     * 根据条件查询玩家列表
     * @param agentFlag 代理标识
     * @param onLineStatus 在线状态
     * @param playStatus 玩家账户状态
     * @return List<PlayerDTO> 玩家列表
     */
    List<PlayerDTO> findPlayersByCondition(Boolean agentFlag,
                                           OnLineStatus onLineStatus ,
                                           PlayStatus playStatus);

    /**
     * 更改玩家信息，代理信息 状态信息除外
     * @param dto 玩家要更新成的信息
     * @return boolean 更新结果
     */
    Boolean updatePlayer(PlayerDTO dto);

    /**
     * 根据ID改变玩家代理等级
     * 仅普通玩家可以更改，普通无上级代理玩家可申请成为商家之外的其他代理
     *                      有上级玩家需根据自己上级代理等级
     * @param id 玩家id
     * @param superAgentCount 上级代理账号id
     * @return Boolean 更新结果
     */
    Boolean updatePlayerAgentTypeById(Long id,Long superAgentCount);

    /**
     * 根据ID改变玩家玩家状态
     * @param id 玩家id
     * @param newPlayStatus 新的玩家状态
     * @return Boolean 更新结果
     */
    Boolean updatePlayerStatusById(Long id ,PlayStatus newPlayStatus);
    /**
     * 查询玩家统计信息
     * @return
     */
    PlayerSummaryDTO findPlayerSummary();

}
