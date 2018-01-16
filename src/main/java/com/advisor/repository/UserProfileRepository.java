package com.advisor.repository;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByUser(User user);

    @Modifying
    @Query("update UserProfile set city = :city where user_profile_id = :userProfileId")
    void updateUserProfile(@Param("city") String city, @Param("userProfileId") Long userProfileId);
}