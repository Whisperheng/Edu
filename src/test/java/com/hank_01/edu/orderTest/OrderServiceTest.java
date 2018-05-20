package com.hank_01.edu.orderTest;

import com.hank_01.edu.BaseTest;
import com.hank_01.edu.dto.OrderDTO;
import com.hank_01.edu.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class OrderServiceTest extends BaseTest{

    @Autowired
    private OrderService service;

    @Test
    public void finishTest(){
        service.finishOrder(1526828374868L);
    }

    @Test
    public void insertTest(){

        service.createOrder(this.createOrder());
    }
    private OrderDTO createOrder(){
        OrderDTO dto = new OrderDTO() ;
        dto.setPlayerName("hankcesi1");
        dto.setPlayerId(1526475041438L);
        dto.setOrderPrice(new BigDecimal(1000L));
        dto.setGoldCount(new BigDecimal(10000));
        return dto;
    }

}
