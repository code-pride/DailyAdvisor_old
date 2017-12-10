package com.advisor.repository;

import com.advisor.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
	RoleEntity findByRole(String role);
}
