package com.kt.edu.thirdproject.common.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestBodyFilter implements Filter {

    @Override
    public void destroy() {
        // filter destroy
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        boolean isAjax = isAjaxRequest((HttpServletRequest) request);
        boolean isJson = isJsonContentType(request);

        try {
            if (isAjax && isJson) {
                RequestBodyWrapper reqWrapper = new RequestBodyWrapper((HttpServletRequest) request);
                chain.doFilter(reqWrapper, response);
            } else {
                chain.doFilter(request, response);
            }
        } catch (IOException e) {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // filter init
    }

    private boolean isJsonContentType(ServletRequest request) {
        String contentType = request.getContentType();
        return (contentType != null && contentType.toLowerCase().contains("json"));
    }

    private boolean isAjaxRequest(HttpServletRequest req){
        return "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
    }

}