package com.hank_01.edu.Entity;

import com.hank_01.edu.enums.AgentType;
import com.hank_01.edu.enums.OnLineStatus;
import com.hank_01.edu.enums.PlayStatus;

public class PlayerEntity extends BaseEntity{
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
}