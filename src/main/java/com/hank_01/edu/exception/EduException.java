package com.hank_01.edu.exception;

import org.slf4j.Logger;

public class EduException extends RuntimeException {

    private Object error;
    public EduException(Object error){
        this.error = error;
    }

    public Object getError() {
        return error;
    }
}
