package com.hank_01.edu.dto;

import com.hank_01.edu.Entity.CommLogEntity;
import com.hank_01.edu.common.util.BeanUtil;

import java.math.BigDecimal;
import java.util.Date;

public class CommLogDTO {
    private Long id;
    private Long agentPlayerId;
    private Long payPlayerId;
    private Long orderId;
    private BigDecimal orderPrice;
    private Long orderGoldAmount;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    private BigDecimal rewardAmount;

    public BigDecimal getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(BigDecimal rewardAmount) {
        this.rewardAmount = rewardAmount;
    }


    public void convertFromEntity(CommLogEntity entity){
        if (entity == null){
            return;
        }
        BeanUtil.copyProperties(entity,this);
    }

    public CommLogEntity convert2Entity(){
        CommLogEntity entity = new CommLogEntity();
        BeanUtil.copyProperties(this , entity);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
