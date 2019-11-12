package com.cooper.articlemanagement.util;

import java.util.Map;

public class ConfigUtil {

    private static ConfigUtil instanse = null;

    private Map<Integer, String> cateporyIdAndNameMap = null;

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

    public static Map<Integer, String> getCateporyIdAndNameMap() {
        return getInstanse().cateporyIdAndNameMap;
    }

    public static void setCateporyIdAndNameMap(Map<Integer, String> cateporyIdAndNameMap) {
        getInstanse().cateporyIdAndNameMap = cateporyIdAndNameMap;
    }
}
