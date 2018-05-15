package com.advisor.repository;

import com.advisor.model.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
    public List<AccessToken> findByUserName(String userName);
}
