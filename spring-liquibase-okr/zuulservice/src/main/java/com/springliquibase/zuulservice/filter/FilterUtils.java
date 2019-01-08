package com.springliquibase.zuulservice.filter;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

@Component
public class FilterUtils {
    public static final String CORRELATION_ID = "is-correlation-id";
    public static final String PRE_FILTER_TYPE = PRE_TYPE;
    public static final String POST_FILTER_TYPE = POST_TYPE;
    public static final String ROUTE_FILTER_TYPE = ROUTE_TYPE;

    public String getCorrelationId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        if(ctx.getRequest().getHeader(CORRELATION_ID) != null) {
            return ctx.getRequest().getHeader(CORRELATION_ID);
        } else {
            return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
        }
    }

    public void setCorrelationId(String correlationId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

}
