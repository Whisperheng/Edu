package com.hank_01.edu.dto;

public class PlayerSummaryDTO {
    private Long onLinePlayerCount;
    private Long playerCount;
    private Long agentCount;

    public Long getOnLinePlayerCount() {
        return onLinePlayerCount;
    }

    public void setOnLinePlayerCount(Long onLinePlayerCount) {
        this.onLinePlayerCount = onLinePlayerCount;
    }

    public Long getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(Long playerCount) {
        this.playerCount = playerCount;
    }

    public Long getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(Long agentCount) {
        this.agentCount = agentCount;
    }
}
