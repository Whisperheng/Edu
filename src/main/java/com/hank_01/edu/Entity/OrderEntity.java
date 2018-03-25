package com.hank_01.edu.Entity;

import com.hank_01.edu.enums.OrderStatus;

import java.math.BigDecimal;

public class OrderEntity extends BaseEntity {
    private Long playerId;
    private String playerName;
    private BigDecimal goldCount;
    private BigDecimal orderPrice;
    private OrderStatus status;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public BigDecimal getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(BigDecimal goldCount) {
        this.goldCount = goldCount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
