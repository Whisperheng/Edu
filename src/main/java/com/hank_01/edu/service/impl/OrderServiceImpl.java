package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.dao.OrderDao;
import com.hank_01.edu.dto.OrderDTO;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.OrderStatus;
import com.hank_01.edu.enums.errorEnum.OrderError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.service.OrderService;
import com.hank_01.edu.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PlayerService playerService;

    @Override
    public Boolean createOrder(OrderDTO orderDTO) {
        if (orderDTO == null){
            throw new EduException(OrderError.PARAMETER_ERROR);
        }
        if (orderDTO.getPlayerId() == null){
            throw new EduException(OrderError.PLAYER_DOES_NOT_EXISTED);
        }
        PlayerDTO playerDTO = playerService.findPlayerById(orderDTO.getPlayerId());
        if (playerDTO == null){
            throw new EduException(OrderError.PLAYER_DOES_NOT_EXISTED);
        }
        orderDTO.setPlayerName(playerDTO.getNickName());
        return orderDao.createOrder(orderDTO.convert2OrderEntity());
    }

    @Transactional
    @Override
    public Boolean finishOrder(Long id) {
        if (id == null){
            return false;
        }
        OrderEntity orderEntity = orderDao.findOrderById(id);
        if (orderEntity == null){
            throw new EduException(OrderError.ORDER_NOT_EXISTED);
        }
        if (orderEntity.getStatus() == OrderStatus.SUCCESS){
            throw new EduException(OrderError.ORDER_ALREADY_FINISH);
        }
        Boolean finishResult = orderDao.updateOrderStatusById(id , OrderStatus.SUCCESS);
        if (!finishResult){
            throw new EduException(OrderError.FINISH_ERROR);
        }
        PlayerDTO playerDTO = playerService.findPlayerById(orderEntity.getPlayerId());
        playerDTO.setGoldCount(playerDTO.getGoldCount().add(orderEntity.getGoldCount()));
        Boolean goldResult = playerService.updatePlayer(playerDTO);
        if (!goldResult){
            throw new EduException(OrderError.ADD_GOLD_COUNT_ERROR);
        }
        if (playerDTO.getSuperAgentLever() == null){
            this.allocOrderMoney2Player(1L,orderEntity.getOrderPrice());
            LOG.debug("无上级，佣金直接给商家");
            return true;
        }
        switch (playerDTO.getSuperAgentLever()){

            case LEVER_SUPER:
                this.allocOrderMoney2Player(playerDTO.getSuperLeverCount(),orderEntity.getOrderPrice());
                LOG.debug("一级代理购买或代理商为商家的普通玩家购买，佣金直接给商家");
                return true;
            case LEVER_1:
                Long superCount = this.allocOrderMoney2Player(playerDTO.getSuperLeverCount(),
                        orderEntity.getOrderPrice().multiply(new BigDecimal(0.8D)));
                LOG.debug("二级代理购买或代理商为一级代理的普通玩家购买，佣金给一级代理0.8");
                this.allocOrderMoney2Player(superCount,orderEntity.getOrderPrice().multiply(new BigDecimal(0.2D)));
                LOG.debug("二级代理购买或代理商为一级代理的普通玩家购买，佣金给商家0.2");
                return true;
            case LEVER_2:
                Long lever1Count = this.allocOrderMoney2Player(playerDTO.getSuperLeverCount(),
                        orderEntity.getOrderPrice().multiply(new BigDecimal(0.72D)));
                LOG.debug("三级代理或代理商为二级代理的普通玩家购买，佣金给二级代理0.72");
                Long superCount2 = this.allocOrderMoney2Player(lever1Count,
                        orderEntity.getOrderPrice().multiply(new BigDecimal(0.2D)));
                LOG.debug("三级代理或代理商为二级代理的普通玩家购买，佣金给一级代理0.2");
                this.allocOrderMoney2Player(superCount2,orderEntity.getOrderPrice().multiply(new BigDecimal(0.08D)));
                LOG.debug("三级代理或代理商为二级代理的普通玩家购买，佣金给商家0.08");
                return true;
            case LEVER_3:
                Long lever2Count = this.allocOrderMoney2Player(playerDTO.getSuperLeverCount(),
                        orderEntity.getOrderPrice().multiply(new BigDecimal(0.7D)));
                LOG.debug("普通玩家（三级代理商推荐）购买，佣金给三级代理0.7");
                Long lever1Count2 = this.allocOrderMoney2Player(lever2Count,
                        orderEntity.getOrderPrice().multiply(new BigDecimal(0.2D)));
                LOG.debug("普通玩家（三级代理商推荐）购买，佣金给二级代理0.2");
                Long superCount3 = this.allocOrderMoney2Player(lever1Count2,
                        orderEntity.getOrderPrice().multiply(new BigDecimal(0.08D)));
                LOG.debug("普通玩家（三级代理商推荐）购买，佣金给一级代理0.08");
                this.allocOrderMoney2Player(superCount3,orderEntity.getOrderPrice().multiply(new BigDecimal(0.02D)));
                LOG.debug("普通玩家（三级代理商推荐）购买，佣金给三级代理0.02");
                return true;
            default:
                this.allocOrderMoney2Player(1L,orderEntity.getOrderPrice());
                LOG.debug("默认为代理商为商家的普通玩家购买，佣金直接给商家");
                return true;
        }

    }

    /**
     * 订单提成 分配
     * @param playerId 玩家上级id
     * @param rewardMoney 奖励金额  / 分成金额
     * @return Long 上级的上级id
     */
    private Long allocOrderMoney2Player(Long playerId , BigDecimal rewardMoney){
        if (playerId == null || rewardMoney == null){
            LOG.error("订单提成分配错误， 参数错误 playerId："+playerId +" rewardMoney: "+ rewardMoney);
            throw new EduException(OrderError.ALLOC_ORDER_MONEY_ERROR);
        }
        PlayerDTO playerDTO = playerService.findPlayerById(playerId);
        if (playerDTO == null ){
            LOG.error("订单提成分配错误, 未找到玩家信息");
            throw new EduException(OrderError.SUPER_LEVER_ERROR_WHEN_ALLOC);
        }
        playerDTO.setMoneyCount(playerDTO.getMoneyCount().add(rewardMoney));
        Boolean result = playerService.updatePlayer(playerDTO);
        if (!result){
            throw new EduException(OrderError.ALLOC_ORDER_MONEY_ERROR);
        }
        return playerDTO.getSuperLeverCount();
    }

    @Override
    public OrderDTO findOrderById(Long id) {
        return null;
    }

    @Override
    public List<OrderDTO> findOrdersByCondition(Long id, Long playerId, OrderStatus status) {
        return null;
    }
}
