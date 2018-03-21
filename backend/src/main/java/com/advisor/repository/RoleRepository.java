package com.advisor.repository;

import com.advisor.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, UUID>{
	Role findByRole(String role);
}
