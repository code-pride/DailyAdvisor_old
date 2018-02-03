package com.advisor.repository;

import com.advisor.model.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("dietRepository")
public interface DietRepository extends JpaRepository<Diet, Integer> {
}
