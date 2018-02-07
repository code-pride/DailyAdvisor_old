package com.advisor.repository;

import com.advisor.model.entity.UserTrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("userTrainrepository")
public interface UserTrainRepository extends JpaRepository<UserTrain, Integer> {

}