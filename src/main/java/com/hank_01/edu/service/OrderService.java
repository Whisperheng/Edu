package com.hank_01.edu.service;

import com.hank_01.edu.dto.OrderDTO;
import com.hank_01.edu.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    /**
     * 新建订单
     * @param orderDTO 订单信息DTO
     * @return Boolean 创建结果
     */
    Boolean createOrder(OrderDTO orderDTO);

    /**
     * 模拟付款逻辑 完成交易 修改订单的状态
     * 完成购买后代理分成的结算和玩家金币的结算
     * @param id 订单id
     * @return Boolean 完成订单结果
     */
    Boolean finishOrder(Long id);

    /**
     * 查询订单
     * @param id 订单id
     * @return OrderDTO 订单详情DTO
     */
    OrderDTO findOrderById(Long id);

    /**
     * 根据条件查询订单列表
     * @param id 订单id
     * @param playerId 玩家id
     * @param status 订单状态
     * @return List<OrderDTO> 订单列表
      */
    List<OrderDTO> findOrdersByCondition(Long id , Long playerId , OrderStatus status );
}
