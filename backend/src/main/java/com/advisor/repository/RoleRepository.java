package com.advisor.repository;

import com.advisor.model.entity.Role;
import org.springframework.stereotype.Repository;

@Repository("roleRepository")
public interface RoleRepository extends SimplyRepository<Role> {
	Role findByRole(String role);
}
