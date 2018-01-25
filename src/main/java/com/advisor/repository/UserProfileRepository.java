package com.advisor.repository;

import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByUser(User user);

    @Transactional
    @Modifying
    @Query("update UserProfile u set u.city = :city, u.about = :about, u.name = :name, u.lastName = :lastName where u.userProfileId = :userProfileId")
    void updateUserProfile(@Param("userProfileId") Long userProfileId, @Param("city") String city, @Param("about") String about, @Param("name") String name, @Param("lastName") String lastName);

}