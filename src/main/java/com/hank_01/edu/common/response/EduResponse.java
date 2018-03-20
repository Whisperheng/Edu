package com.hank_01.edu.common.response;

import com.github.pagehelper.Page;
import com.hank_01.edu.common.pagination.Pagination;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EduResponse {

    public final static int SUCC_CODE = 200;

    public final static String SUCC_MSG = "OK";

    public final static int ERROR_CODE = -1;

    public final static String ERROR_MSG = "系统异常，稍后重试";

    public int errorCode = HttpStatus.OK.value();

    public String message;

    public Object data = null;


    public EduResponse() {}

    public EduResponse(Object data) {
        this.data = data;
    }

    public EduResponse(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public EduResponse(int errorCode, String message, Object data) {
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public static EduResponse succResponse() {
        return new EduResponse(SUCC_CODE, SUCC_MSG);
    }

    public static EduResponse succResponse(Object data) {
        EduResponse response = new EduResponse(SUCC_CODE, SUCC_MSG);
        response.setData(data);
        return response;
    }

    public static EduResponse succResponseByCheckingPagination(List<?> data){
        EduResponse response = new EduResponse(SUCC_CODE, SUCC_MSG);
        if (data!=null&&data instanceof Page){
            response.setData(new Pagination(data));
        }else{
            response.setData(data);
        }
        return response;
    }

    public static EduResponse errorResponse() {
        return new EduResponse(ERROR_CODE, ERROR_MSG);
    }

    public static EduResponse errorResponse(Object data) {
        EduResponse response = new EduResponse(ERROR_CODE, ERROR_MSG);
        response.setData(data);
        return response;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
