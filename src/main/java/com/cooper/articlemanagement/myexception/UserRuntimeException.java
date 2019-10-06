package com.cooper.articlemanagement.myexception;

import java.util.Date;

public class UserRuntimeException extends RuntimeException {
    public UserRuntimeException(String message) {
        super("[" + new Date() + "] User: " + message);
    }
}
