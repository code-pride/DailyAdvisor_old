package com.advisor.repository;


import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("advertisementRepository")
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

    Advertisement findByUser(User user);

    @Query("select a from Advertisement a")
    List<Advertisement> selectAll();

}
