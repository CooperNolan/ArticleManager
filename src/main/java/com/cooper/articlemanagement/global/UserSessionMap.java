package com.cooper.articlemanagement.global;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.http.HttpSession;

public class UserSessionMap {

    private static ConcurrentMap<Integer, HttpSession> userSessionMap = new ConcurrentHashMap<Integer, HttpSession>();

    public static ConcurrentMap<Integer, HttpSession> getUserSessionMap() {
        return userSessionMap;
    }

    public static void putUserSessionMap(Integer key, HttpSession value) {
        destoryHttpSession(key);
        userSessionMap.put(key, value);
    }

    public static void removeUserSessionMap(Integer key) {
        userSessionMap.remove(key);
    }

    public static HttpSession getHttpSession(Integer key) {
        return userSessionMap.get(key);
    }

    public static void destoryHttpSession(Integer key) {
        HttpSession session = userSessionMap.get(key);
        if (session != null) {
            session.invalidate();
            userSessionMap.remove(key);
        }
    }
}
