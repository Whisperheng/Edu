package com.hank_01.edu.controller;

import com.hank_01.edu.common.response.EduResponse;
import com.hank_01.edu.dto.OrderDTO;
import com.hank_01.edu.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/order")
@Api(value = "订单相关api")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "创建新订单")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public EduResponse createOrder(@ApiParam(value = "玩家id")@RequestParam(value = "playerId") Long playerId,
                                   @ApiParam(value = "玩家购买金币数量")@RequestParam(value = "goldCount") Long goldCount,
                                   @ApiParam(value = "订单金额")@RequestParam(value = "orderPrice") Long orderPrice){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setGoldCount(new BigDecimal(goldCount));
        orderDTO.setOrderPrice(new BigDecimal(orderPrice));
        orderDTO.setPlayerId(playerId);
        return EduResponse.succResponse(orderService.createOrder(orderDTO));
    }

    @ApiOperation(value = "完成订单 / 支付订单")
    @RequestMapping(value = "/finish",method = RequestMethod.PUT)
    public EduResponse finishOrder(@ApiParam(value = "订单id")@RequestParam(value = "orderId") Long orderId){
        return EduResponse.succResponse(orderService.finishOrder(orderId));
    }

}
