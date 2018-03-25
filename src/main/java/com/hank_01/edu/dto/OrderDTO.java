package com.hank_01.edu.dto;

import com.hank_01.edu.Entity.OrderEntity;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {
    private Long id;
    private Long playerId;
    private String playerName;
    private BigDecimal goldCount;
    private BigDecimal orderPrice;
    private OrderStatus status;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    public OrderEntity convert2OrderEntity(){
        OrderEntity  entity = new OrderEntity();
        BeanUtil.copyProperties(this ,entity);
        return entity;
    }

    public void convertFromEntity(OrderEntity entity){
        BeanUtil.copyProperties(entity,this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
