package com.cooper.articlemanagement.global;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 编码过滤器 默认UTF-8
 */
public class EncodingFilter implements Filter {

    String encoding = "UTF-8";

    public void destroy() {
        encoding = "UTF-8";
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("ENCODING");
        if(encoding == null){
            encoding = "UTF-8";
        }
    }

}
