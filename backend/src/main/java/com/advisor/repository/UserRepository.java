package com.advisor.repository;


import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id=:id")
    User findByIdd(@Param("id") UUID id);

}
