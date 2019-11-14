package com.cooper.articlemanagement.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ConfigUtil {

    private static ConfigUtil instanse = null;

    private Map<Integer, String> categoryIdAndNameMap = null;

    private Set<String> loginInterceptorSet = null;

    private ConfigUtil() {}

    public static ConfigUtil getInstanse() {
        if (instanse == null) {
            synchronized (ConfigUtil.class) {
                if (instanse == null) {
                    instanse = new ConfigUtil();
                }
            }
        }
        return instanse;
    }

    public static void init() {
        getInstanse();

    }

    public static Map<Integer, String> getCategoryIdAndNameMap() {
        return getInstanse().categoryIdAndNameMap;
    }

    public static void setCategoryIdAndNameMap(Map<Integer, String> categoryIdAndNameMap) {
        getInstanse().categoryIdAndNameMap = categoryIdAndNameMap;
    }

    public static Set<String> getLoginInterceptorSet(HttpServletRequest request) {
        if (getInstanse().loginInterceptorSet == null) {
            String initStr = request.getServletContext().getInitParameter("excludeMappingLogin");
            getInstanse().loginInterceptorSet = new HashSet<>(Arrays.asList(initStr.split(",")));
        }
        return getInstanse().loginInterceptorSet;
    }
}
