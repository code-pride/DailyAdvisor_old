package com.advisor.repository;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("dietRepository")
public interface DietRepository extends JpaRepository<Diet, Integer> {

    @Query("SELECT d FROM Diet d WHERE d.id = :id AND d.createdBy = :creator")
    List<Diet> findByCreatorAndId(@Param("creator") User creator, @Param("id") long id);

    Diet findOneById(long dietId);

}
