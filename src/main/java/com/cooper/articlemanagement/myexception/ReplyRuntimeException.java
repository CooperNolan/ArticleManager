package com.cooper.articlemanagement.myexception;

import java.util.Date;

public class ReplyRuntimeException extends RuntimeException {
    public ReplyRuntimeException(String message) {
        super("[" + new Date() + "] " + message);
    }
}
