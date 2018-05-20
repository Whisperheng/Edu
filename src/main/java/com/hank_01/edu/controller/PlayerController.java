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

import java.math.BigDecimal;

@RestController
@CrossOrigin
@RequestMapping(value = "/players" )
@Api(description = "玩家相关 api")
public class PlayerController {

    @Autowired
    private PlayerService  playerService;


    @ApiOperation(value = "查找玩家",notes = "查找玩家")
    @RequestMapping(value = "/player/{id}" ,method = RequestMethod.GET)
    public EduResponse findPlayerById(@ApiParam(value = "玩家id")@PathVariable(value = "id") Long id){
        return EduResponse.succResponse(playerService.findPlayerById(id));
    }


    @ApiOperation(value = "查找玩家列表",notes = "查找玩家列表")
    @RequestMapping(value = "" ,method = RequestMethod.GET)
    public EduResponse findPlayersByCondition(@ApiParam(value = "代理等级")@RequestParam(value = "agentFlag",required = false)Boolean agentFlag,
                                              @ApiParam(value = "在线状态")@RequestParam(value = "onLineStatus",required = false)OnLineStatus onLineStatus,
                                              @ApiParam(value = "玩家状态")@RequestParam(value = "playerStatus",required = false)PlayStatus playStatus){

        return EduResponse.succResponse(playerService.findPlayersByCondition(agentFlag,onLineStatus,playStatus));
    }


    @RequestMapping(value = "/summary" ,method = RequestMethod.GET)
    public EduResponse findPlayerSummary(){
        return EduResponse.succResponse(playerService.findPlayerSummary());
    }


    @ApiOperation(value = "更新玩家信息",notes = "更新玩家信息")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public EduResponse updatePlayer(@ApiParam(value = "更新请求")@RequestBody PlayerRequest request){
        return EduResponse.succResponse(playerService.updatePlayer(this.convertRequest2DTO(request)));
    }


    @ApiOperation(value = "申请玩家代理等级",notes = "申请玩家代理等级")
    @RequestMapping(value = "/agent" ,method = RequestMethod.PUT)
    public EduResponse updatePlayerAgentLeverById(@ApiParam(value = "玩家ID")@RequestParam(value = "id")Long id,
                                                  @ApiParam(value = "代理ID")@RequestParam(value = "agentCount")Long agentCount){
        return EduResponse.succResponse(playerService.updatePlayerAgentTypeById(id,agentCount));
    }


    @ApiOperation(value = "新建玩家",notes = "新建玩家")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public EduResponse createPlayer(@RequestBody PlayerRequest request){
        return EduResponse.succResponse(playerService.createPlayer(this.convertRequest2DTO(request)));
    }

    @ApiOperation(value = "查找玩家可申请代理列表",notes = "查找玩家可申请代理列表")
    @RequestMapping(value = "/available/agents",method = RequestMethod.GET)
    public EduResponse findPlayerAvailable(@ApiParam(value = "玩家ID")@RequestParam(value = "id") Long id){
        return EduResponse.succResponse(playerService.agentQualification(id));
    }

    /**
     * 转 request 为  playerDTO
     * @param request
     * @return PlayerDTO
     */
    private PlayerDTO convertRequest2DTO(PlayerRequest request){
        if (request == null){
            return null;
        }
        PlayerDTO dto = new PlayerDTO();
        dto.setWeChatId(request.getWeChatId());
        dto.setWeChatName(request.getWeChatName());
        dto.setNickName(request.getNickName());
        dto.setSex(request.getSex());
        dto.setSuperLeverCount(request.getSuperLeverCount());
        dto.setGoldCount(new BigDecimal(request.getGoldCount()));
        dto.setMoneyCount(new BigDecimal(request.getMoneyCount()));
        return dto;
    }

}
