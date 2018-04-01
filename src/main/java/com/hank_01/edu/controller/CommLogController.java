package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.dto.CommLogDTO;
import com.hank_01.edu.request.CommLogRequest;
import com.hank_01.edu.service.CommLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/comm_log")
@CrossOrigin
@Api(value = "佣金分配日志api")
public class CommLogController {

    @Autowired
    private CommLogService commLogService;

    @ApiOperation("根据id查询佣金分配详情")
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public EduResponse findCommLog(@PathVariable(value = "id" ,required = true)Long id){
        return EduResponse.succResponse(commLogService.findById(id));
    }

    @ApiOperation(("根据条件查询佣金分配信息列表"))
    @RequestMapping(value = "/comm_logs" , method = RequestMethod.GET)
    public EduResponse findCommLogs(@RequestParam(value = "agentPlayerId",required = false)Long agentPlayerId,
                                    @RequestParam(value = "orderId",required = false)Long orderId,
                                    @RequestParam(value = "startTime",required = false)Long startTime,
                                    @RequestParam(value = "endTime",required = false)Long endTime){

        Date start = null;
        Date End = null;
        if (startTime != null){
            start = new Date(startTime);
        }
        if (endTime != null){
            End = new Date(endTime);
        }
        return EduResponse.succResponse(commLogService.findByCondition(agentPlayerId,orderId,start,End));
    }

    @ApiOperation("增加佣金分配详情")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public EduResponse insertCommLog(@RequestBody CommLogRequest request){
        CommLogDTO dto = this.convertDTOFromRequest(request);
        return EduResponse.succResponse(commLogService.insertCommLog(dto));
    }

    private CommLogDTO convertDTOFromRequest(CommLogRequest request){
        if (request == null){
            return null;
        }
        CommLogDTO dto = new CommLogDTO();
        BeanUtil.copyProperties(request ,dto);
        return dto;
    }
}
