package com.advisor.service.Exceptions;

import org.springframework.http.HttpStatus;

public class EntityExists extends DataRepositoryException {

    private static final String STANDARD_MESSAGE_CODE = "exception.entityExistsException";

    private static final HttpStatus STANDARD_RESPONSE_CODE = HttpStatus.IM_USED;

    public EntityExists() {
        super();
    }

    public EntityExists(String overriddenMessageCode, Object[] messageParams, String exceptionMessage) {
        super(overriddenMessageCode, messageParams, exceptionMessage);
    }

    public EntityExists(String overriddenMessageCode, Object[] messageParams) {
        super(overriddenMessageCode, messageParams);
    }

    public EntityExists(String overriddenMessageCode) {
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
