package com.hank_01.edu.Entity;

import java.math.BigDecimal;

public class CommLogEntity extends BaseEntity{
    private Long agentPlayerId;
    private Long payPlayerId;
    private Long orderId;
    private BigDecimal orderPrice;
    private Long orderGoldAmount;
    private BigDecimal rewardAmount;

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public Long getAgentPlayerId() {
        return agentPlayerId;
    }

    public void setAgentPlayerId(Long agentPlayerId) {
        this.agentPlayerId = agentPlayerId;
    }

    public Long getPayPlayerId() {
        return payPlayerId;
    }

    public void setPayPlayerId(Long payPlayerId) {
        this.payPlayerId = payPlayerId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getOrderGoldAmount() {
        return orderGoldAmount;
    }

    public void setOrderGoldAmount(Long orderGoldAmount) {
        this.orderGoldAmount = orderGoldAmount;
    }
}
