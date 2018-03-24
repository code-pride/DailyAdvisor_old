package com.advisor.repository;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository("dietRepository")
public interface DietRepository extends SimplyRepository<Diet> {

    @Query("SELECT d FROM Diet d WHERE d.id = :id AND d.createdBy = :creator")
    List<Diet> findByCreatorAndId(@Param("creator") User creator, @Param("id") UUID id);

    Diet findOneById(UUID dietId);

    List<Diet> findByCreatedBy(User user);
}
