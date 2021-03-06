package com.hank_01.edu.dto;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.math.BigDecimal;
import java.util.Date;

public class PlayerDTO {
    private Long id;
    private String weChatId;
    private String weChatName;
    private String nickName;
    private String sex;
    private PlayStatus status;
    private OnLineStatus onLineStatus;
    private BigDecimal moneyCount;
    private BigDecimal goldCount;
    private Boolean agentFlag;
    private Long superLeverCount;
    private String superLeverName;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    public PlayerEntity convert2Entity(){
        PlayerEntity entity =new PlayerEntity();
        BeanUtil.copyProperties(this,entity);
        return entity;
    }

    public void convertFrom(PlayerEntity entity){
        if (entity == null){
            return ;
        }
        BeanUtil.copyProperties(entity,this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWeChatId() {
        return weChatId;
    }

    public void setWeChatId(String weChatId) {
        this.weChatId = weChatId;
    }

    public String getWeChatName() {
        return weChatName;
    }

    public void setWeChatName(String weChatName) {
        this.weChatName = weChatName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public PlayStatus getStatus() {
        return status;
    }

    public void setStatus(PlayStatus status) {
        this.status = status;
    }

    public OnLineStatus getOnLineStatus() {
        return onLineStatus;
    }

    public void setOnLineStatus(OnLineStatus onLineStatus) {
        this.onLineStatus = onLineStatus;
    }

    public BigDecimal getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        this.moneyCount = moneyCount;
    }

    public BigDecimal getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(BigDecimal goldCount) {
        this.goldCount = goldCount;
    }

    public Boolean getAgentFlag() {
        return agentFlag;
    }

    public void setAgentFlag(Boolean agentFlag) {
        this.agentFlag = agentFlag;
    }

    public Long getSuperLeverCount() {
        return superLeverCount;
    }

    public void setSuperLeverCount(Long superLeverCount) {
        this.superLeverCount = superLeverCount;
    }

    public String getSuperLeverName() {
        return superLeverName;
    }

    public void setSuperLeverName(String superLeverName) {
        this.superLeverName = superLeverName;
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
