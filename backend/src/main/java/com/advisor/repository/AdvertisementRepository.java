package com.advisor.repository;

import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.CoachType;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository("advertisementRepository")
public interface AdvertisementRepository extends SimplyRepository<Advertisement> {

    Advertisement findByUser(User user);

    @Transactional
    @Modifying
    @Query("update Advertisement a set a.advText = :advText where a.user = :user")
    void updateAdvertisement(@Param("advText") String advText, @Param("user") User user);

    Advertisement findByUserAndStatus(User user, String status);

    List<Advertisement> findByStatus(String status);

    @Transactional
    @Modifying
    @Query("update Advertisement a set a.status = :status where a.user = :user")
    int updateStatus(@Param("user") User user, @Param("status") String status);

    @Query("SELECT a FROM Advertisement a WHERE a.user IN :users AND a.coachType = :coachType AND a.status = :status")
    List<Advertisement> findByUserInAndCoachTypeAndStatus(@Param("users") List<User> users, @Param("coachType") CoachType coachType, @Param("status") String status);
}
