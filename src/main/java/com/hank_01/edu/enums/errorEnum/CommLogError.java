package com.hank_01.edu.enums.errorEnum;

import com.hank_01.edu.exception.IEduException;

public enum CommLogError implements IEduException {

    PARAMETER_ERROR(4001,"参数错误，创建新佣金日志参数为空"),
    ORDER_NOT_EXISTED(4002,"该订单不存在"),
    AGENT_PLAYER_ERROR(4003,"代理玩家账号为空或不存在"),
    ORDER_PRICE_ERROR(4004,"订单金额为空"),
    ORDER_GOLD_COUNT_ERROR(4005,"订购金币数量为空"),
    PAY_PLAYER_ERROR(4006,"购买金币玩家为空或不存在"),
    REWARD_AMOUNT_ERROR(4007,"佣金为空！！"),
    INSERT_COMM_LOG_FAILED(400,"插入佣金分配日志失败！"),
    ;

    private int errorCode;
    private String message;

    CommLogError(int code , String message){
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
