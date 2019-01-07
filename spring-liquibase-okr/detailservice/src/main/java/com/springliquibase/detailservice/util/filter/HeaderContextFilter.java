package com.springliquibase.detailservice.util.filter;

import com.springliquibase.detailservice.util.HeaderContext;
import com.springliquibase.detailservice.util.HeaderContextHolder;
import com.springliquibase.detailservice.util.HttpServletHeaderRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class HeaderContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("HeaderContextFilter -> init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("HeaderContextFilter -> doFilter");

        HttpServletHeaderRequestWrapper httpRequest = new HttpServletHeaderRequestWrapper((HttpServletRequest) request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String correlationId = httpRequest.getHeader(HeaderContext.CORRELATION_ID);
        log.debug("HeaderContextFilter -> doFilter: correlationId {}", correlationId);

        if (HeaderContextHolder.getContext().isCorrelationIdExist(correlationId)) {
            log.debug("HeaderContextFilter -> doFilter: correlationId exist: {}", correlationId);
            HeaderContextHolder.getContext().setCorrelationId(correlationId);
        } else {
            log.debug("HeaderContextFilter -> doFilter: correlationId not exist");
            httpRequest.setHeader(HeaderContext.CORRELATION_ID, HeaderContextHolder.getContext().getCorrelationId());
        }

        httpResponse.addHeader(HeaderContext.CORRELATION_ID, HeaderContextHolder.getContext().getCorrelationId());

        log.debug("HeaderContextFilter -> doFilter: request correlationId: {}", httpRequest.getHeader(HeaderContext.CORRELATION_ID));
        log.debug("HeaderContextFilter -> doFilter: correlationId: {}", HeaderContextHolder.getContext().getCorrelationId());
        log.debug("HeaderContextFilter -> doFilter: requestURI: {}", httpRequest.getRequestURI());

        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
        log.debug("HeaderContextFilter -> destroy");
    }

}
