package com.hank_01.edu.request;

import com.hank_01.edu.enums.AgentLever;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

import java.util.Date;

public class PlayerRequest {
    private Long id;
    private String weChatId;
    private String weChatName;
    private String nickName;
    private String sex;
    private PlayStatus playStatus;
    private OnLineStatus onLineStatus;
    private Long moneyCount;
    private Long goldCount;
    private AgentLever agentLever;
    private Long superLeverCount;
    private String superLeverName;
    private Date createTime;
    private Date updateTime;
    private Boolean isDeleted;

    /******* 扩展字段 *************/

    private Long superCountId;
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

    public AgentLever getAgentLever() {
        return agentLever;
    }

    public void setAgentLever(AgentLever agentLever) {
        this.agentLever = agentLever;
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
