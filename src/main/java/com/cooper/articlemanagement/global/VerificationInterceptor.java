package com.cooper.articlemanagement.global;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.UserStateEnum;
import com.cooper.articlemanagement.util.ConfigUtil;
import com.cooper.articlemanagement.util.HttpUtil;

/**
 * url拦截 未登录访问限制
 */
public class VerificationInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(VerificationInterceptor.class);

    private static ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        User user = (User)request.getSession().getAttribute("USER");
        String uri = request.getRequestURI();
        startTime.set(System.currentTimeMillis());
        if (user != null) {
            logger.info("<<<<<<<<<<<< " + user.getUsername() + "(" + HttpUtil.getIpAddress(request) + "):" + uri
                + " access start <<<<<<<<<<<<");
        } else {
            logger.info("<<<<<<<<<<<< " + HttpUtil.getIpAddress(request) + ":" + uri + " access start <<<<<<<<<<<<");
        }
        // 判断是否拦截
        if (isIntercept(uri, request)) {
            if (user == null) {
                response.sendRedirect(HttpUtil.getBasePath(request) + "/" + UserStateEnum.LOGOUT_SUCCESS.getMsgOrUrl());
                return false;
            }
        } else {
            if (user != null) {
                response.sendRedirect(HttpUtil.getBasePath(request) + "/" + UserStateEnum.LOGIN_SUCCESS.getMsgOrUrl());
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        User user = (User)request.getSession().getAttribute("USER");
        String uri = request.getRequestURI();
        Long consumeTime = System.currentTimeMillis() - startTime.get();
        startTime.remove();
        if (user != null) {
            logger.info(">>>>>>>>>>>> " + user.getUsername() + "(" + HttpUtil.getIpAddress(request) + "):" + uri
                + " access end (耗时：" + consumeTime + "ms) >>>>>>>>>>>>");
        } else {
            logger.info(">>>>>>>>>>>> " + HttpUtil.getIpAddress(request) + ":" + uri + " access end (耗时：" + consumeTime
                + "ms) >>>>>>>>>>>>");
        }
    }

    /**
     * 解析未登录放行uri初始值值
     * 
     * @param uri
     * @param request
     * @return
     */
    public boolean isIntercept(String uri, HttpServletRequest request) {
        if (ConfigUtil.getLoginInterceptorSet(request).contains(uri)) {
            return false;
        }
        return true;
    }
}
