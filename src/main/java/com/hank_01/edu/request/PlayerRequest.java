package com.hank_01.edu.request;

import com.hank_01.edu.enums.AgentLever;

public class PlayerRequest {

    private Long id;
    private String weChatId;
    private String weChatName;
    private String nickName;
    private String sex;
    private Long moneyCount;
    private Long goldCount;
    private AgentLever agentLever;
    private Long superLeverCount;
    private String superLeverName;

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

}
