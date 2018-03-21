package com.hank_01.edu.enums;

import org.apache.commons.lang3.StringUtils;

public enum AgentLever {
    LEVER_SUPER(0,"LEVER_SUPER","商家账户，总代理"),
    LEVER_1(1,"LEVER_1","一级代理"),
    LEVER_2(2,"LEVER_2","二级代理"),
    LEVER_3(3,"LEVER_3","三级代理"),
    LEVER_NULL(4,"LEVER_NULL","非代理普通玩家")
    ;
    private int code;
    private String name;
    private String desc;

    AgentLever(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static AgentLever findByName(String name){
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (AgentLever current : values()) {
            if (StringUtils.equals(current.name(), name)) {
                return current;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
