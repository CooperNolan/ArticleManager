package com.cooper.articlemanagement.util;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResponseBodyUtil {
    public static void responseBody(boolean success, String msgOrUrl, HttpServletResponse response) throws IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", success);
        if (success) {
            modelMap.put("url", msgOrUrl);
        } else {
            modelMap.put("msg", msgOrUrl);
        }
        response.getWriter().println(JSON.toJSONString(modelMap));
    }

    public static void replyResponseBody(boolean success, String msgOrUrl, Integer replyId,HttpServletResponse response) throws IOException {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("success", success);
        modelMap.put("msg", msgOrUrl);
        modelMap.put("replyId", replyId);
        response.getWriter().println(JSON.toJSONString(modelMap));
    }
}
