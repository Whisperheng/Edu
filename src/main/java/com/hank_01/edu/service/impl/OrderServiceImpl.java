package com.hank_01.edu.service.impl;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.common.util.CollectionUtil;
import com.hank_01.edu.dao.OrderDao;
import com.hank_01.edu.dto.CommLogDTO;
import com.hank_01.edu.dto.OrderDTO;
import com.hank_01.edu.dto.PlayerDTO;
import com.hank_01.edu.enums.OrderStatus;
import com.hank_01.edu.enums.errorEnum.CommLogError;
import com.hank_01.edu.enums.errorEnum.OrderError;
import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.service.CommLogService;
import com.hank_01.edu.service.OrderService;
import com.hank_01.edu.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PlayerService playerService;
    @Autowired
    private CommLogService commLogService;

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

    private CommLogDTO createCommLogCriteria(OrderEntity orderEntity ){
        CommLogDTO commLogDTO = new CommLogDTO();
        commLogDTO.setOrderId(orderEntity.getId());
        commLogDTO.setPayPlayerId(orderEntity.getPlayerId());
        commLogDTO.setOrderPrice(orderEntity.getOrderPrice());
        commLogDTO.setOrderGoldAmount(orderEntity.getGoldCount().longValue());
        return commLogDTO;
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
        //完成订单 更改订单状态
        Boolean finishResult = orderDao.updateOrderStatusById(id , OrderStatus.SUCCESS);
        if (!finishResult){
            throw new EduException(OrderError.FINISH_ERROR);
        }
        PlayerDTO playerDTO = playerService.findPlayerById(orderEntity.getPlayerId());
        if (playerDTO == null){
            throw new EduException(OrderError.INVALID_ORDER_PLAYER_INVALID);
        }
        if (playerDTO.getMoneyCount().compareTo(orderEntity.getOrderPrice())<0){
            throw new EduException(OrderError.MONEY_NOT_ENOUGH);
        }
        // 将玩家购买的金币到账
        playerDTO.setGoldCount(playerDTO.getGoldCount().add(orderEntity.getGoldCount()));
        playerDTO.setMoneyCount(playerDTO.getMoneyCount().subtract(orderEntity.getOrderPrice()));
        Boolean goldResult = playerService.updatePlayer(playerDTO);
        if (!goldResult){
            throw new EduException(OrderError.ADD_GOLD_COUNT_ERROR);
        }
        // 分佣 为对应的上级分佣
        if (playerDTO.getSuperLeverCount() == null){
            playerDTO.setSuperLeverCount(1L);
            playerDTO.setSuperLeverName("商家");
        }
        this.allocCommission(playerDTO,orderEntity);
        return true;
    }

    /**
     * 完成佣金分配，订单购买金额和虚拟商品充值
     * @param playerDTO
     * @param orderEntity
     */
    private void allocCommission(PlayerDTO playerDTO,OrderEntity orderEntity){
        if (playerDTO == null || orderEntity == null ){
            LOG.error("参数错误。 分佣时错误 ");
            return;
        }
        if (playerDTO.getSuperLeverCount() == null){
            playerDTO.setSuperLeverCount(1L);
        }
        PlayerDTO agentPlayer1 = playerService.findPlayerById(playerDTO.getSuperLeverCount());
        if (agentPlayer1 == null){
            throw new EduException(OrderError.SUPER_LEVER_ERROR_WHEN_ALLOC);
        }
        if (agentPlayer1.getId() == 1L){
            agentPlayer1.setMoneyCount(agentPlayer1.getMoneyCount().add(orderEntity.getOrderPrice()));
            playerService.updatePlayer(agentPlayer1);
            this.insertCommissionLog(orderEntity,agentPlayer1,new BigDecimal(1));
            return;
        }
        agentPlayer1.setMoneyCount(agentPlayer1.getMoneyCount().add(orderEntity.getOrderPrice().multiply(new BigDecimal(0.7))));
        playerService.updatePlayer(agentPlayer1);
        this.insertCommissionLog(orderEntity,agentPlayer1,new BigDecimal(0.7));
        if (agentPlayer1.getSuperLeverCount() == null){
            throw new EduException(OrderError.SUPER_LEVER_ERROR_WHEN_ALLOC);
        }
        PlayerDTO agentPlayer2 = playerService.findPlayerById(agentPlayer1.getSuperLeverCount());
        if (agentPlayer2 == null){
            throw new EduException(OrderError.SUPER_LEVER_ERROR_WHEN_ALLOC);
        }
        if (agentPlayer2.getId() == 1L){
            agentPlayer2.setMoneyCount(agentPlayer2.getMoneyCount().add(orderEntity.getOrderPrice().multiply(new BigDecimal(0.3))));
            playerService.updatePlayer(agentPlayer2);
            this.insertCommissionLog(orderEntity,agentPlayer2,new BigDecimal(0.3));
            return;
        }
        agentPlayer2.setMoneyCount(agentPlayer2.getMoneyCount().add(orderEntity.getOrderPrice().multiply(new BigDecimal(0.2))));
        playerService.updatePlayer(agentPlayer2);
        this.insertCommissionLog(orderEntity,agentPlayer2,new BigDecimal(0.2));
        PlayerDTO agentPlayer3 = playerService.findPlayerById(1L);
        agentPlayer3.setMoneyCount(agentPlayer3.getMoneyCount().add(orderEntity.getOrderPrice().multiply(new BigDecimal(0.1))));
        playerService.updatePlayer(agentPlayer3);
        this.insertCommissionLog(orderEntity,agentPlayer3,new BigDecimal(0.1));

    }

    /**
     * 插入佣金日志
     * @param orderEntity
     * @param agentPlayer
     * @param commissionRate
     */
    private void insertCommissionLog(OrderEntity orderEntity , PlayerDTO agentPlayer ,BigDecimal commissionRate){
        if (orderEntity == null || agentPlayer == null){
            LOG.error("插入分佣日志时出错！");
            return;
        }
        CommLogDTO commLogDTO = new CommLogDTO();
        commLogDTO.setPayPlayerId(orderEntity.getPlayerId());
        commLogDTO.setOrderId(orderEntity.getId());
        commLogDTO.setAgentPlayerId(agentPlayer.getId());
        commLogDTO.setOrderPrice(orderEntity.getOrderPrice());
        if (orderEntity.getGoldCount() != null){
            commLogDTO.setOrderGoldAmount(orderEntity.getGoldCount().longValue());
        }
        commLogDTO.setRewardAmount(orderEntity.getOrderPrice().multiply(commissionRate));
        commLogService.insertCommLog(commLogDTO);
    }
    /**
     * 订单提成 分配
     * @param agentPlayerId 玩家上级id
     * @param rewardMoney 奖励金额  / 分成金额
     * @return Long 上级的上级id
     */
    private Long allocOrderMoney2Player(Long agentPlayerId , BigDecimal rewardMoney , OrderEntity orderEntity){
        if (agentPlayerId == null || rewardMoney == null){
            LOG.error("订单提成分配错误， 参数错误 playerId："+agentPlayerId +" rewardMoney: "+ rewardMoney);
            throw new EduException(OrderError.ALLOC_ORDER_MONEY_ERROR);
        }
        PlayerDTO playerDTO = playerService.findPlayerById(agentPlayerId);
        if (playerDTO == null ){
            LOG.error("订单提成分配错误, 未找到玩家信息");
            throw new EduException(OrderError.SUPER_LEVER_ERROR_WHEN_ALLOC);
        }
        playerDTO.setMoneyCount(playerDTO.getMoneyCount().add(rewardMoney));
        Boolean result = playerService.updatePlayer(playerDTO);
        if (!result){
            throw new EduException(OrderError.ALLOC_ORDER_MONEY_ERROR);
        }
        LOG.debug("分配佣金成功 被分配的代理："+ agentPlayerId + "分配的金额："+rewardMoney.doubleValue());
        CommLogDTO commLogDTO = this.createCommLogCriteria(orderEntity);
        commLogDTO.setAgentPlayerId(agentPlayerId);
        commLogDTO.setPayPlayerId(orderEntity.getPlayerId());
        commLogDTO.setRewardAmount(rewardMoney);
        Boolean commResult = commLogService.insertCommLog(commLogDTO);
        if (!commResult){
            throw new EduException(CommLogError.INSERT_COMM_LOG_FAILED);
        }
        return playerDTO.getSuperLeverCount();
    }

    @Override
    public OrderDTO findOrderById(Long id) {
        if (id == null){
            return null;
        }
        OrderEntity entity = orderDao.findOrderById(id);
        if (entity == null){
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.convertFromEntity(entity);
        return orderDTO;
    }

    @Override
    public List<OrderDTO> findOrdersByCondition(Long id, Long playerId, OrderStatus status) {
        List<OrderEntity> orderEntities = orderDao.findOrdersByCondition(id,status,playerId);
        if (CollectionUtil.isEmpty(orderEntities)){
            return null;
        }
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderEntity entity : orderEntities){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.convertFromEntity(entity);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }
}
