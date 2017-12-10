package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user", schema = "daily_advisor", catalog = "")
public class UserEntity {
    private int userId;
    private Integer active;
    private String email;
    private String lastName;
    private String name;
    private String password;
    private Collection<AdvertisementEntity> advertisementsByUserId;
    private Collection<CalendarEntity> calendarsByUserId;
    private Collection<DietEntity> dietsByUserId;
    private Collection<DietEntity> dietsByUserId_0;
    private Collection<MeetingEntity> meetingsByUserId;
    private Collection<MeetingEntity> meetingsByUserId_0;
    private Collection<TrainEntity> trainsByUserId;
    private Collection<TrainEntity> trainsByUserId_0;
    private Collection<UserProfileEntity> userProfilesByUserId;
    private Collection<RoleEntity> roles;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))


    public Collection<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Collection<RoleEntity> roles) {
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<AdvertisementEntity> getAdvertisementsByUserId() {
        return advertisementsByUserId;
    }

    public void setAdvertisementsByUserId(Collection<AdvertisementEntity> advertisementsByUserId) {
        this.advertisementsByUserId = advertisementsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CalendarEntity> getCalendarsByUserId() {
        return calendarsByUserId;
    }

    public void setCalendarsByUserId(Collection<CalendarEntity> calendarsByUserId) {
        this.calendarsByUserId = calendarsByUserId;
    }

    @OneToMany(mappedBy = "userByAuthorId")
    public Collection<DietEntity> getDietsByUserId() {
        return dietsByUserId;
    }

    public void setDietsByUserId(Collection<DietEntity> dietsByUserId) {
        this.dietsByUserId = dietsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<DietEntity> getDietsByUserId_0() {
        return dietsByUserId_0;
    }

    public void setDietsByUserId_0(Collection<DietEntity> dietsByUserId_0) {
        this.dietsByUserId_0 = dietsByUserId_0;
    }

    @OneToMany(mappedBy = "userByAuthorId")
    public Collection<MeetingEntity> getMeetingsByUserId() {
        return meetingsByUserId;
    }

    public void setMeetingsByUserId(Collection<MeetingEntity> meetingsByUserId) {
        this.meetingsByUserId = meetingsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<MeetingEntity> getMeetingsByUserId_0() {
        return meetingsByUserId_0;
    }

    public void setMeetingsByUserId_0(Collection<MeetingEntity> meetingsByUserId_0) {
        this.meetingsByUserId_0 = meetingsByUserId_0;
    }

    @OneToMany(mappedBy = "userByAuthorId")
    public Collection<TrainEntity> getTrainsByUserId() {
        return trainsByUserId;
    }

    public void setTrainsByUserId(Collection<TrainEntity> trainsByUserId) {
        this.trainsByUserId = trainsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<TrainEntity> getTrainsByUserId_0() {
        return trainsByUserId_0;
    }

    public void setTrainsByUserId_0(Collection<TrainEntity> trainsByUserId_0) {
        this.trainsByUserId_0 = trainsByUserId_0;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserProfileEntity> getUserProfilesByUserId() {
        return userProfilesByUserId;
    }

    public void setUserProfilesByUserId(Collection<UserProfileEntity> userProfilesByUserId) {
        this.userProfilesByUserId = userProfilesByUserId;
    }
}
