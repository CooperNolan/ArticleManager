package com.cooper.articlemanagement.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class ResponseBodyUtil {

    public static String responseBody(boolean success, String msgOrUrl) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", success);
        if (success) {
            modelMap.put("url", msgOrUrl);
        } else {
            modelMap.put("msg", msgOrUrl);
        }
        return JSON.toJSONString(modelMap);
    }

    public static Map<String, Object> responseBody(boolean success, String msgOrUrl, boolean b) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", success);
        if (success) {
            modelMap.put("url", msgOrUrl);
        } else {
            modelMap.put("msg", msgOrUrl);
        }
        return modelMap;
    }

    public static String replyResponseBody(boolean success, String msg, Integer replyId) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", success);
        modelMap.put("msg", msg);
        modelMap.put("replyId", replyId);
        return JSON.toJSONString(modelMap);
    }
}
