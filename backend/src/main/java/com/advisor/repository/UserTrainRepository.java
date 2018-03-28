package com.advisor.repository;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository("userTrainRepository")
public interface UserTrainRepository extends JpaRepository<UserTrain, UUID> {


    List<UserTrain> findOneByUserAndTrain(User user, Train train);

    List<UserTrain> findByUser(User user);
}