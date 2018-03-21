package com.hank_01.edu.enums.errorEnum;

import com.hank_01.edu.exception.IEduException;

public enum PlayerError implements IEduException {
    PLAYER_DOES_NOT_EXISTED(2001,"玩家账户不存在"),
    ;

    private int errorCode;
    private String message;

    PlayerError(int code , String message){
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
