package com.hank_01.edu.enums.errorEnum;

import com.hank_01.edu.exception.IEduException;

public enum UserErrorEnum implements IEduException{
    PARAMETER_ERROR(1001,"参数错误，注册账号必须包含登录名和密码"),
    USER_COUNT_NOT_EXISTED(1002,"该用户账号不存在"),
    USER_COUNT_ALREADY_EXISTED(1003,"该用户名已注册，请更换用户名后重试"),
    CELLPHONE_IS_INVALID(1004,"手机号错误，请输入正确的手机号"),
    PASSWORD_ERROR(1005,"登录密码错误！请确认后重试"),
    ;

    private int errorCode;
    private String message;

    UserErrorEnum(int code , String message){
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
