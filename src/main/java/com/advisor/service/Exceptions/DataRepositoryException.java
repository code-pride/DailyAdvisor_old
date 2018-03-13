package com.advisor.service.Exceptions;

import org.springframework.http.HttpStatus;

public class DataRepositoryException extends ApplicationException {

    private static final String STANDARD_MESSAGE_CODE = "exception.dataRepositoryException";

    private static final HttpStatus STANDARD_RESPONSE_CODE = HttpStatus.INTERNAL_SERVER_ERROR;

    public DataRepositoryException() {
        super();
    }

    public DataRepositoryException(String overridenMessaegCode, Object[] messageParams, String exceptionMessage) {
        super(overridenMessaegCode, messageParams, exceptionMessage);
    }

    public DataRepositoryException(String overridenMessageCode, Object[] messageParams) {
        super(overridenMessageCode, messageParams);
    }

    public DataRepositoryException(String overridenMessageCode) {
        super(overridenMessageCode);
    }

    @Override
    public String getStandardMessageCode() {
        return STANDARD_MESSAGE_CODE;
    }

    @Override
    public HttpStatus getStandardResponseCode() {
        return STANDARD_RESPONSE_CODE;
    }
}

