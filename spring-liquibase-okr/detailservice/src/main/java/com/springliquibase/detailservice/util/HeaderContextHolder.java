package com.springliquibase.detailservice.util;

public class HeaderContextHolder {

    private static final ThreadLocal<HeaderContext> headerContext = new ThreadLocal<>();

    public static HeaderContext getContext() {

        if (headerContext.get() == null) {
            setContext(createEmptyContext());
        }

        return headerContext.get();
    }

    private static void setContext(HeaderContext context) {
        headerContext.set(context);
    }

    private static HeaderContext createEmptyContext() {
        return new HeaderContext();
    }

}
