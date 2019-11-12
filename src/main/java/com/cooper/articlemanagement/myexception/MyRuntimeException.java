package com.cooper.articlemanagement.myexception;

public class MyRuntimeException extends RuntimeException {

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(String message, Throwable e) {
        super(message, e);
    }

}
