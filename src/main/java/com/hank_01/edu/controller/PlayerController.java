package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/player" )
public class PlayerController {

    @Autowired
    private PlayerService  playerService;
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public EduResponse findPlayerById(@PathVariable(value = "id") Long id){
        return EduResponse.succResponse(playerService.findPlayerById(id));
    }

    @RequestMapping(value = "/players" ,method = RequestMethod.GET)
    public EduResponse findPlayersByCondition(@RequestParam(value = "agentLever",required = false)AgentType agentType,
                                              @RequestParam(value = "onLineStatus",required = false)OnLineStatus onLineStatus,
                                              @RequestParam(value = "playerStatus",required = false)PlayStatus playStatus){

        return EduResponse.succResponse(playerService.findPlayersByCondition(agentType,onLineStatus,playStatus));
    }
}
