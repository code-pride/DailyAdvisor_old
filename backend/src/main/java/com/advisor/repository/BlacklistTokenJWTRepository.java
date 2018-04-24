package com.advisor.repository;

import com.advisor.model.entity.BlacklistTokenJWT;
import org.springframework.stereotype.Repository;

@Repository("blacklistRepository")
public interface BlacklistTokenJWTRepository extends SimplyRepository<BlacklistTokenJWT>{

}
