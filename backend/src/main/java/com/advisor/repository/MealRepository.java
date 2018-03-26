package com.advisor.repository;

import com.advisor.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("mealRepository")
public interface MealRepository extends JpaRepository<Meal, UUID> {

}