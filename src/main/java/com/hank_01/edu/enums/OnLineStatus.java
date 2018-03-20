package com.hank_01.edu.enums;

import org.apache.commons.lang3.StringUtils;

public enum OnLineStatus {
    ONLINE(1,"ONLINE","账号在线"),
    OFFLINE(2,"OFFLINE","账号下线")
    ;
    private int code;
    private String name;
    private String desc;

    OnLineStatus(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static OnLineStatus findByName(String name){
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (OnLineStatus current : values()) {
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
