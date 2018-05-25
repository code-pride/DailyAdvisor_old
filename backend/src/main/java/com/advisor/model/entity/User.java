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
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @Transient
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToOne
    @PrimaryKeyJoinColumn
    private UserProfile userProfile;

    @Column(name = "enabled")
    private boolean enabled;


    public User(NewUserRequest newUserRequest) {
        this.setPassword(newUserRequest.getPassword());
        this.setEmail(newUserRequest.getEmail());
        this.setActive(false);
        this.enabled = false;
    }

    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
