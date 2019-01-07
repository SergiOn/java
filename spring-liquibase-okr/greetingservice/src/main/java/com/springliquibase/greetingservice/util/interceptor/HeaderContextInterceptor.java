package com.springliquibase.greetingservice.util.interceptor;

import com.springliquibase.greetingservice.util.HeaderContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class HeaderContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("HeaderContextInterceptor -> preHandle");
        log.debug("HeaderContextInterceptor -> preHandle: correlationId: {}", request.getHeader(HeaderContext.CORRELATION_ID));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.debug("HeaderContextInterceptor -> postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.debug("HeaderContextInterceptor -> afterCompletion");
    }
}
