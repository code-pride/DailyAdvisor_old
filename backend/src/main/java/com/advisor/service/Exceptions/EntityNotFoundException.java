package com.advisor.service.Exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DataRepositoryException {

    private static final String STANDARD_MESSAGE_CODE = "exception.entityNotFoundException";

    private static final HttpStatus STANDARD_RESPONSE_CODE = HttpStatus.NOT_FOUND;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String overriddenMessageCode, Object[] messageParams, String exceptionMessage) {
        super(overriddenMessageCode, messageParams, exceptionMessage);
    }

    public EntityNotFoundException(String overriddenMessageCode, Object[] messageParams) {
        super(overriddenMessageCode, messageParams);
    }

    public EntityNotFoundException(String overriddenMessageCode) {
        super(overriddenMessageCode);
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