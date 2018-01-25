package com.advisor.model.entity;

import java.util.Set;
import javax.persistence.*;
import com.advisor.model.request.NewUserRequest;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	@Transient
	private String password;

	@Column(name = "active")
	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

//	@OneToMany(cascade= CascadeType.ALL, mappedBy="userEvent")
//	private Set<Event> events;

//	@OneToMany(mappedBy = "userId")
//	private Set<Meeting> meetings;
	@OneToOne
	@PrimaryKeyJoinColumn
	private UserProfile userProfile;



    public User(NewUserRequest newUserRequest) {
        this.setPassword(newUserRequest.getPassword());
        this.setEmail(newUserRequest.getEmail());
    }

    public User() {
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
}
