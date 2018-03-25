package com.hank_01.edu.service;

import com.hank_01.edu.dto.OrderDTO;
import com.hank_01.edu.enums.OrderStatus;

import java.util.List;

public interface OrderService {
    /**
     * 新建订单
     * @param orderDTO
     * @return Boolean
     */
    Boolean createOrder(OrderDTO orderDTO);

    /**
     * 模拟付款逻辑 完成交易 修改订单的状态
     * 完成购买后代理分成的结算和玩家金币的结算
     * @param id
     * @return Boolean
     */
    Boolean finishOrder(Long id);

    /**
     * 查询订单
     * @param id
     * @return OrderDTO
     */
    OrderDTO findOrderById(Long id);

    /**
     * 根据条件查询订单列表
     * @param id
     * @param playerId
     * @param status
     * @return List<OrderDTO>
     */
    List<OrderDTO> findOrdersByCondition(Long id , Long playerId , OrderStatus status );
}
