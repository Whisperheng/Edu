package com.hank_01.edu.enums;

import org.apache.commons.lang3.StringUtils;

public enum OrderStatus {
    UNPAID(1,"UNPAID","订单未付款"),
    SUCCESS(2,"SUCCESS","订单成功")
    ;
    private int code;
    private String name;
    private String desc;

    OrderStatus(int code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public static OrderStatus findByName(String name){
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (OrderStatus current : values()) {
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
