package com.cooper.articlemanagement.global;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Date;

@Aspect
public class LogAdvice {

    @Pointcut("execution(* com.cooper.articlemanagement.service.*.*(..))")
    public void allExecution(){}

    @Before("allExecution()")
    public void befo(JoinPoint joinPoint) {
        System.out.println("[" + new Date() + "] 数据库访问中...");
    }

    @AfterReturning("allExecution()")
    public void operationLog(JoinPoint joinPoint){
        System.out.println("[" + new Date() + "] 数据库访问结束...");
    }

    @AfterThrowing("allExecution()")
    public void systemLog(JoinPoint joinPoint){
        System.out.println(joinPoint.toString());
    }
}
