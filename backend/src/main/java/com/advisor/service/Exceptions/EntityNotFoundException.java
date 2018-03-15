package com.advisor.service.Exceptions;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DataRepositoryException {

    private static final long serialVersionUID = 4579170430548337393L;

    private static final String STANDARD_MESSAGE_CODE = "exception.entityNotFoundException";

    private static final HttpStatus STANDARD_RESPONSE_CODE = HttpStatus.NOT_FOUND;

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(String overridenMessaegCode, Object[] messageParams, String exceptionMessage) {
        super(overridenMessaegCode, messageParams, exceptionMessage);
    }

    public EntityNotFoundException(String overridenMessageCode, Object[] messageParams) {
        super(overridenMessageCode, messageParams);
    }

    public EntityNotFoundException(String overridenMessageCode) {
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