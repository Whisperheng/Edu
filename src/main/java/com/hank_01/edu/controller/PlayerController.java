package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;
import com.hank_01.edu.request.PlayerRequest;
import com.hank_01.edu.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/player" )
@Api(description = "player")
public class PlayerController {

    @Autowired
    private PlayerService  playerService;
    @ApiOperation(value = "sahsbhaschajcjk",notes = "sadcdsdcva")
    @RequestMapping(value = "/{id}" ,method = RequestMethod.GET)
    public EduResponse findPlayerById(@ApiParam(value = "woashajsca")@PathVariable(value = "id") Long id){
        return EduResponse.succResponse(playerService.findPlayerById(id));
    }

    @RequestMapping(value = "/players" ,method = RequestMethod.GET)
    public EduResponse findPlayersByCondition(@RequestParam(value = "agentLever",required = false)AgentLever agentLever,
                                              @RequestParam(value = "onLineStatus",required = false)OnLineStatus onLineStatus,
                                              @RequestParam(value = "playerStatus",required = false)PlayStatus playStatus){

        return EduResponse.succResponse(playerService.findPlayersByCondition(agentLever,onLineStatus,playStatus));
    }

    @RequestMapping(value = "",method = RequestMethod.PUT)
    public EduResponse updatePlayer(@RequestBody PlayerRequest request){
        return EduResponse.succResponse(playerService.updatePlayer(this.convertRequest2DTO(request)));
    }

    @RequestMapping(value = "/agent" ,method = RequestMethod.PUT)
    public EduResponse updatePlayerAgentLeverById(@RequestParam(value = "id")Long id,
                                                  @RequestParam(value = "newAgentLever",required = false)AgentLever newAgentLever){
        return EduResponse.succResponse(playerService.updatePlayerAgentTypeById(id,newAgentLever));
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public EduResponse createPlayer(@RequestBody PlayerRequest request){
        return EduResponse.succResponse(playerService.createPlayer(this.convertRequest2DTO(request)));
    }

    private PlayerDTO convertRequest2DTO(PlayerRequest request){
        PlayerDTO dto = new PlayerDTO();
        BeanUtil.copyProperties(request,dto);
        return dto;
    }
}
