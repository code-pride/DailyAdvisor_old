package com.advisor.model.entity;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import com.advisor.model.request.NewUserRequest;
import lombok.Data;
import org.springframework.data.annotation.Transient;

@Entity
@Data
@Table(name = "user_")
public class User {

	@Id
	@org.hibernate.annotations.Type(type = "pg-uuid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id",unique=true, nullable = false)
	private UUID id;

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

	@OneToOne
	@PrimaryKeyJoinColumn
	private UserProfile userProfile;


    public User(NewUserRequest newUserRequest) {
        this.setPassword(newUserRequest.getPassword());
        this.setEmail(newUserRequest.getEmail());
    }

    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getActive() == user.getActive() &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRoles(), user.getRoles()) &&
                Objects.equals(getUserProfile(), user.getUserProfile());
    }
}
