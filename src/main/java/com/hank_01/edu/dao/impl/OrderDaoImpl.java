package com.hank_01.edu.dao.impl;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.dao.OrderDao;
import com.hank_01.edu.enums.OrderStatus;
import com.hank_01.edu.enums.errorEnum.OrderError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOG = LoggerFactory.getLogger(OrderDaoImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Boolean createOrder(OrderEntity entity) {
        if (entity == null){
            LOG.debug("创建订单失败 ，参数无效");
            return false ;
        }
        if (entity.getPlayerId() == null || entity.getOrderPrice()== null || entity.getGoldCount() ==null){
            throw new EduException(OrderError.PARAMETER_ERROR);
        }
        entity.setId(new Date().getTime());
        entity.setStatus(OrderStatus.UNPAID);
        return orderMapper.createOrder(entity);
    }

    @Override
    public OrderEntity findOrderById(Long id) {
        if (id == null){
            LOG.debug("参数错误： Order id为空");
            return null;
        }
        return orderMapper.findOrderById(id);
    }

    @Override
    public Boolean updateOrderStatusById(Long id, OrderStatus status) {
        if (id==null || status == null){
            return false;
        }
        OrderEntity entity = this.findOrderById(id);
        if (entity == null){
            throw new EduException(OrderError.ORDER_NOT_EXISTED);
        }
        return orderMapper.updateOrderStatusById(id, status);
    }

    @Override
    public List<OrderEntity> findOrdersByCondition(Long id ,OrderStatus status, Long playerId) {
        return orderMapper.findOrdersByCondition(id,status,playerId);
    }
}
