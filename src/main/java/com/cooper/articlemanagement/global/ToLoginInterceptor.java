package com.cooper.articlemanagement.global;

import com.cooper.articlemanagement.enums.UserStateEnum;
import com.cooper.articlemanagement.util.GetPathUtil;
import com.cooper.articlemanagement.util.StringToListUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * url拦截 未登录访问限制
 */
public class ToLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("[" + new Date() + "] " + request.getRequestURL());
        //判断是否拦截
        if (isIntercept(request.getRequestURI(), request)) {
            if (request.getSession().getAttribute("USER") == null) {
                response.sendRedirect(GetPathUtil.getBasePath(request) + "/"  + UserStateEnum.LOGOUT_SUCCESS.getMsgOrUrl());
                return false;
            }
        } else {
            if (request.getSession().getAttribute("USER") != null) {
                response.sendRedirect(GetPathUtil.getBasePath(request) + "/"  + UserStateEnum.LOGIN_SUCCESS.getMsgOrUrl());
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 解析未登录放行uri初始值值
     * @param uri
     * @param request
     * @return
     */
    public boolean isIntercept(String uri,HttpServletRequest request) {
        String initStr = request.getServletContext().getInitParameter("excludeMappingLogin");
        List<String> list = StringToListUtil.stringToList(initStr, ",");
        for (String str : list) {
            if (uri.equals(str)) {
                return false;
            }
        }
        return true;
    }
}
