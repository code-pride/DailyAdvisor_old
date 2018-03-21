package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@Table(name = "role")
public class Role {
	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique=true, nullable = false)
	private UUID id;

	@Column(nullable = false, unique = true, name="role")
	private String role;


	public Role(String role) {
		this.role = role;
	}

	public Role() {
	}

}
