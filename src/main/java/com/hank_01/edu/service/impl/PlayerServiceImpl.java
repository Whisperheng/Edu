package com.hank_01.edu.service.impl;

import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {
    @Override
    public PlayerDTO findPlayerById(Long id) {
        return null;
    }

    @Override
    public List<PlayerDTO> findPlayersByCondition(AgentType agentType, OnLineStatus onLineStatus, PlayStatus playStatus) {
        return null;
    }

    @Override
    public Boolean updatePlayer(PlayerDTO dto) {
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
