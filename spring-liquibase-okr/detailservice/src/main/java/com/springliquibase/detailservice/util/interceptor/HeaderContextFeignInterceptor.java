package com.springliquibase.detailservice.util.interceptor;

import com.springliquibase.detailservice.util.HeaderContext;
import com.springliquibase.detailservice.util.HeaderContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HeaderContextFeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.debug("HeaderContextFeignInterceptor -> apply");

        String correlationId = HeaderContextHolder.getContext().getCorrelationId();
        requestTemplate.header(HeaderContext.CORRELATION_ID, correlationId);

        log.debug("HeaderContextFeignInterceptor -> apply: correlationId: {}", correlationId);
    }

}
