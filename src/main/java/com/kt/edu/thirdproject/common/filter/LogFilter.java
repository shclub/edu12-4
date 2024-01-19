package com.kt.edu.thirdproject.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@Slf4j
@Component
public class LogFilter implements Filter {
    @Override
    public void init(
            FilterConfig filterConfig
    ) throws ServletException {
        log.info("LogFilter init()");
    }

    @Override
    public void destroy() {
        log.info("LogFilter destroy()");
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        String requestURI = req.getRequestURI();

        log.info("[{}] LogFilter doFilter Start", requestURI);

        MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
        printRequest(multiReadRequest);

        if (multiReadRequest.getMethod().equals("POST")){
            ServletInputStream inputStream = multiReadRequest.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("[{}] LogFilter doFilter Body",messageBody);
        }
        try {
            chain.doFilter(multiReadRequest, response);
        } finally {
            log.info("[{}] LogFilter doFilter End", requestURI);
        }
    }

    // 참고
    //https://velog.io/@saint6839/Controller%EC%97%90%EC%84%9C-HttpRequest-Body-%EA%B0%92%EC%9D%80-%EC%99%9C-%EB%B9%84%EC%9B%8C%EC%A0%B8-%EC%9E%88%EC%9D%84%EA%B9%8C

    private  void printRequest(HttpServletRequest httpRequest) {
        log.info(" Headers");

        Enumeration headerNames = httpRequest.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String headerName = (String)headerNames.nextElement();
            log.info(headerName + " = " + httpRequest.getHeader(headerName));
        }

        log.info(" Parameters");

        Enumeration params = httpRequest.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = (String)params.nextElement();
            log.info(paramName + " = " + httpRequest.getParameter(paramName));
        }
        log.info(" Row data");
    }
}