package com.hank_01.edu.dao;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.enums.OrderStatus;

public interface OrderDao {


    /**
     * 新建订单
     * @param entity
     * @return Boolean
     */
    Boolean createOrder(OrderEntity entity);

    /**
     * 根据订单id查找订单
     * @param id
     * @return OrderEntity
     */
    OrderEntity findOrderById(Long id );

    /**
     * 更改订单状态
     * @param id
     * @param status
     * @return
     */
    Boolean updateOrderStatusById(Long id , OrderStatus status);
}
