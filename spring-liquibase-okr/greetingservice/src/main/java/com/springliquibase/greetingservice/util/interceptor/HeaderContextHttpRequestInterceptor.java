package com.springliquibase.greetingservice.util.interceptor;

import com.springliquibase.greetingservice.util.HeaderContext;
import com.springliquibase.greetingservice.util.HeaderContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class HeaderContextHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add(HeaderContext.CORRELATION_ID, HeaderContextHolder.getContext().getCorrelationId());

        log.debug("HeaderContextHttpRequestInterceptor -> intercept: correlationId: {}", HeaderContextHolder.getContext().getCorrelationId());

        return execution.execute(request, body);
    }
}
