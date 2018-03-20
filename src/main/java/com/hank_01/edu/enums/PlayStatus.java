package com.hank_01.edu.enums;

import org.apache.commons.lang3.StringUtils;

public enum PlayStatus {
    USE(1,"USE","账号使用中"),
    STOP(2,"STOP","账号被停用")
    ;
    private int code;
    private String name;
    private String desc;

    PlayStatus(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static PlayStatus findByName(String name){
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (PlayStatus current : values()) {
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
