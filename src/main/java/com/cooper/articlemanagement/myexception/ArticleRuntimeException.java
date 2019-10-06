package com.cooper.articlemanagement.myexception;

import java.util.Date;

public class ArticleRuntimeException extends RuntimeException{
    public ArticleRuntimeException(String message) {
        super("[" + new Date() + "] Article" + message);
    }
}
