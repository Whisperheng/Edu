package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.request.PlayerRequest;
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

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public EduResponse updatePlayer(@RequestBody PlayerRequest request){
        return EduResponse.succResponse(playerService.updatePlayer(this.convertRequest2DTO(request)));
    }

    private PlayerDTO convertRequest2DTO(PlayerRequest request){
        PlayerDTO dto = new PlayerDTO();
        BeanUtil.copyProperties(request,dto);
        return dto;
    }
}
