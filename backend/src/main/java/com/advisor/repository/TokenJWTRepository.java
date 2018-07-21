package com.advisor.repository;

import com.advisor.model.entity.TokenJWT;
import org.springframework.stereotype.Repository;

@Repository("blacklistRepository")
public interface TokenJWTRepository extends SimplyRepository<TokenJWT>{

}
