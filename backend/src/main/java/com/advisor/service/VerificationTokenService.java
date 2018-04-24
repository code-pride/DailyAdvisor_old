package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.entity.VerificationToken;
import com.advisor.service.Exceptions.EntityNotFoundException;

import java.util.UUID;

public interface VerificationTokenService extends IService<VerificationToken, UUID> {
    User confirmToken(String token) throws EntityNotFoundException;
}
