package com.advisor.repository;


import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("advertisementRepository")
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {

}
