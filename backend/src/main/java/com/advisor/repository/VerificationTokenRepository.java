package com.advisor.repository;

import com.advisor.model.entity.VerificationToken;
import org.springframework.stereotype.Repository;


@Repository("verificationTokenRepository")
public interface VerificationTokenRepository extends SimplyRepository<VerificationToken> {

    VerificationToken findOneByToken(String token);
}
