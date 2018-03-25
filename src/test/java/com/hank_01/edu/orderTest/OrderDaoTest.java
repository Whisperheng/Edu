package com.hank_01.edu.orderTest;

import com.hank_01.edu.BaseTest;
import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.dao.OrderDao;
import com.hank_01.edu.enums.OrderStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class OrderDaoTest extends BaseTest {

    @Autowired
    private OrderDao orderDao;

    @Test
    public void findOrderTest(){
        OrderEntity orderEntity = orderDao.findOrderById(1L);
        System.out.print(orderEntity.getStatus());
    }

    @Test
    public void createTest(){
        OrderEntity entity = new OrderEntity();
        BigDecimal goldCount = new BigDecimal(2121L);
        entity.setGoldCount(goldCount);
        entity.setOrderPrice(goldCount);
        entity.setPlayerId(1L);
        entity.setPlayerName("hank");

        Boolean result  = orderDao.createOrder(entity);
        System.out.print(result);
    }
    @Test
    public void updateStatus(){
        Boolean re = orderDao.updateOrderStatusById(1L, OrderStatus.SUCCESS);
        System.out.print(re);
    }
}
