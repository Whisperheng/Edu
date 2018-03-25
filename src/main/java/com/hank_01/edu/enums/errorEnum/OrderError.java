package com.hank_01.edu.enums.errorEnum;

import com.hank_01.edu.exception.IEduException;

public enum OrderError implements IEduException {
    PARAMETER_ERROR(3001,"参数错误，创建新订单参数不够"),
    ORDER_NOT_EXISTED(3002,"该订单不存在"),
    FINISH_ERROR(3003,"订单完成失败"),
    ALLOC_ORDER_MONEY_ERROR(3004,"订单提成 分配失败"),
    SUPER_LEVER_ERROR_WHEN_ALLOC(3005,"订单提成 分配失败, 未找到玩家信息，无法打款"),
    ADD_GOLD_COUNT_ERROR(3006,"购买失败, 增加金币失败"),
    PLAYER_DOES_NOT_EXISTED(3007,"订单创建失败， ： 玩家不存在"),
    ORDER_ALREADY_FINISH(3008,"订单已完成，请勿重复付款"),
    ;

    private int errorCode;
    private String message;

    OrderError(int code , String message){
        this.errorCode = code;
        this.message = message;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
