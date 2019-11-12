package com.cooper.articlemanagement.global;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
public class LogAdvice {

    @Pointcut("execution(* com.cooper.articlemanagement.service.*.*(..))")
    public void allExecution() {}

    @Before("allExecution()")
    public void befo(JoinPoint joinPoint) {
        System.out.println("[" + new Date() + "] 数据库访问中...");
    }

    @AfterReturning("allExecution()")
    public void operationLog(JoinPoint joinPoint) {
        System.out.println("[" + new Date() + "] 数据库访问结束...");
    }

    @AfterThrowing("allExecution()")
    public void systemLog(JoinPoint joinPoint) {
        System.out.println(joinPoint.toString());
    }

    public HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
        return servletRequestAttributes.getRequest();
    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        if (null != ipAddress && ipAddress.length() > 15) {
            ipAddress = ipAddress.split(",")[0];
        }

        return ipAddress;
    }
}
