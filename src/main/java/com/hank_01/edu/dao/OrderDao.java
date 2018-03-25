package com.hank_01.edu.dao;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.enums.OrderStatus;

import java.util.List;

public interface OrderDao {


    /**
     * 新建订单
     * @param entity 订单详情石体
     * @return Boolean 订单创建结果
     */
    Boolean createOrder(OrderEntity entity);

    /**
     * 根据订单id查找订单
     * @param id 订单id
     * @return OrderEntity 订单信息
     */
    OrderEntity findOrderById(Long id );

    /**
     * 更改订单状态
     * @param id 订单id
     * @param status 想要更改成的订单状态
     * @return 更新结果
     */
    Boolean updateOrderStatusById(Long id , OrderStatus status);

    /**
     * 根据参数查找订单列表
     * @param id 订单id
     * @param status 订单状态
     * @param playerId 玩家id
     * @return 订单列表
     */
    List<OrderEntity> findOrdersByCondition(Long id ,OrderStatus status, Long playerId);
}
