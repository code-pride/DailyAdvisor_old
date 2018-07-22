package com.advisor.repository;


import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository("userRepository")
public interface UserRepository extends SimplyRepository<User> {

    User findByEmail(String email);

}
