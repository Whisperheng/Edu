package com.hank_01.edu.enums.errorEnum;

import com.hank_01.edu.exception.IEduException;

public enum PlayerError implements IEduException {
    PLAYER_DOES_NOT_EXISTED(2001,"玩家账户不存在"),
    CAN_NOT_CHANGE_AGENT_LEVER(2002,"代理等级固定，不允许更改，仅允许从普通玩家申请成为代理"),
    PARAMETER_IS_NOT_ENOUGH(2003,"创建新玩家时 ，参数不够，必选参数：微信ID 昵称 "),
    SUPER_LEVER_COUNT_IS_INVALID(2004,"创建新玩家失败 ！ ，reason： 上级代理账号无效 ！")
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
