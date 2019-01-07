package com.springliquibase.greetingservice.util;

public class HeaderContext {

    public static final String CORRELATION_ID = "is-correlation-id";
    private String correlationId;

    public boolean isCorrelationIdExist(String value) {
        return value != null && !value.equals("");
    }

    public String getCorrelationId() {

        if (!isCorrelationIdExist(correlationId)) {
            setCorrelationId(generateCorrelationId());
        }

        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }

}
