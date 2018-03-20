package com.hank_01.edu.enums;

import org.apache.commons.lang3.StringUtils;

public enum UserStatus {

    WAIT(1,"WAIT","注册后待管理员审批"),
    USE(2,"USE","账号使用中"),
    STOP(3,"STOP","账号被停用")
    ;
    private int code;
    private String name;
    private String desc;

    UserStatus(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static UserStatus findByName(String name){
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (UserStatus current : values()) {
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
