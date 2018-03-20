package com.hank_01.edu.dto;

import com.hank_01.edu.Entity.PlayerEntity;
import com.hank_01.edu.common.util.BeanUtil;
import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.util.Date;

public class PlayerDTO {
    private Long id;
    private String weChatId;
    private String weChatName;
    private String nickName;
    private String sex;
    private PlayStatus playStatus;
    private OnLineStatus onLineStatus;
    private Long moneyCount;
    private Long goldCount;
    private AgentType agentType;
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

    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(PlayStatus playStatus) {
        this.playStatus = playStatus;
    }

    public OnLineStatus getOnLineStatus() {
        return onLineStatus;
    }

    public void setOnLineStatus(OnLineStatus onLineStatus) {
        this.onLineStatus = onLineStatus;
    }

    public Long getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Long moneyCount) {
        this.moneyCount = moneyCount;
    }

    public Long getGoldCount() {
        return goldCount;
    }

    public void setGoldCount(Long goldCount) {
        this.goldCount = goldCount;
    }

    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
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
