package com.advisor.repository;

import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository("advertisementRepository")
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    Advertisement findByUser(User user);

    @Transactional
    @Modifying
    @Query("update Advertisement a set a.advText = :advText where a.user = :user")
    void updateAdvertisement(@Param("advText") String advText, @Param("user") User user);

    Advertisement findByUserAndStatus(User user, String active);

    List<Advertisement> findByStatus(String active);

    @Transactional
    @Modifying
    @Query("update Advertisement a set a.status = :status where a.user = :user AND a.advId = :advId")
    int updateStatus(@Param("advId") long advId, @Param("user") User user, @Param("status") String status);
}
