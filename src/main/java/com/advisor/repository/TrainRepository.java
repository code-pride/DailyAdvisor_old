package com.advisor.repository;

import com.advisor.model.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("trainRepository")
public interface TrainRepository extends JpaRepository<Train, Integer> {

}