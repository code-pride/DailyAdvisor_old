package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_profile", schema = "daily_advisor", catalog = "")
public class UserProfileEntity {
    private int userProfileId;
    private String about;
    private String city;
    private Double weight;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_profile_id")
    public int getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(int userProfileId) {
        this.userProfileId = userProfileId;
    }

    @Basic
    @Column(name = "about")
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "weight")
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserProfileEntity that = (UserProfileEntity) o;

        if (userProfileId != that.userProfileId) return false;
        if (about != null ? !about.equals(that.about) : that.about != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userProfileId;
        result = 31 * result + (about != null ? about.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
