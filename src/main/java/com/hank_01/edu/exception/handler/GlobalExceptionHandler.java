package com.hank_01.edu.exception.handler;

import com.hank_01.edu.exception.EduException;
import com.hank_01.edu.exception.IEduException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    @ResponseBody
    String handleException(Exception e){
        if (e instanceof EduException){
            int code = ((IEduException)((EduException) e).getError()).getErrorCode();
            String message = ((IEduException)((EduException) e).getError()).getMessage();
            return " code : " + code +" , message : " + message;
        }
        return e.getMessage();
    }
}
