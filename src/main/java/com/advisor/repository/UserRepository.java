package com.advisor.repository;

import com.advisor.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	 UserEntity findByEmail(String email);
}
