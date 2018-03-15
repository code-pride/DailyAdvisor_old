package com.advisor.service.Exceptions;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends Exception {

    private static final String APPLICATION_DEFAULT_MESSAGE = "exception.applicationException";

    private static final HttpStatus APPLICATION_DEFAULT_RESPONSE_CODE = HttpStatus.INTERNAL_SERVER_ERROR;

    private HttpStatus overridenResponseCode;

    private String overridenMessageCode;

    private Object[] messageParams;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String overridenMessageCode) {
        super();
        this.overridenMessageCode = overridenMessageCode;
    }

    public ApplicationException(String overridenMessageCode, Object[] messageParams) {
        super();
        this.overridenMessageCode = overridenMessageCode;
        this.messageParams = messageParams;
    }

    public ApplicationException(String overridenMessaegCode, Object[] messageParams, String exceptionMessage) {
        super(exceptionMessage);
        this.overridenMessageCode = overridenMessaegCode;
        this.messageParams = messageParams;
    }

    abstract public String getStandardMessageCode();

    abstract public HttpStatus getStandardResponseCode();

    public String getLocalizationMessageCode() {
        if (getOverridenMessageCode() == null) {
            String code = getStandardMessageCode();
            return (code == null ? APPLICATION_DEFAULT_MESSAGE : code);
        } else {
            return getOverridenMessageCode();
        }
    }

    public HttpStatus getResponseCode() {
        if (getOverridenResponseCode() == null) {
            HttpStatus code = getStandardResponseCode();
            return (code == null ? APPLICATION_DEFAULT_RESPONSE_CODE : code);
        } else {
            return getOverridenResponseCode();
        }
    }

    public HttpStatus getOverridenResponseCode() {
        return overridenResponseCode;
    }

    public void setOverridenResponseCode(HttpStatus overridenResponseCode) {
        this.overridenResponseCode = overridenResponseCode;
    }

    public String getOverridenMessageCode() {
        return overridenMessageCode;
    }

    public void setOverridenMessageCode(String overridenMessageCode) {
        this.overridenMessageCode = overridenMessageCode;
    }

    public Object[] getMessageParams() {
        return messageParams;
    }

    public void setMessageParams(Object[] messageParams) {
        this.messageParams = messageParams;
    }

}