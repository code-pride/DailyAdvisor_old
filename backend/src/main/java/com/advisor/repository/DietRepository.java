package com.advisor.repository;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository("dietRepository")
public interface DietRepository extends JpaRepository<Diet, UUID> {

    List<Diet> findByCreatedBy(User user);

    Diet findOneByCreatedByAndId(User user, UUID dietId);

    @Query("SELECT d FROM Diet d WHERE d.id = :uuid")
    Diet findOneByUUID(@Param("uuid") UUID uuid);

    Diet findOneById(UUID id);
}
