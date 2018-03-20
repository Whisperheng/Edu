package com.hank_01.edu.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserTypeEnum {
    BUYER(1, "BUYER", "用户"),
    TENANT_USER(2, "TENANT_USER", "租户"),
    SYSTEM_USER(3, "SYSTEM_USER", "系统用户");

    private int code;
    private String name;
    private String desc;

    UserTypeEnum(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static UserTypeEnum findByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (UserTypeEnum current : values()) {
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
