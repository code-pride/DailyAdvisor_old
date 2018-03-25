package com.advisor.repository;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository("dietRepository")
public interface DietRepository extends JpaRepository<Diet, UUID> {

    Diet findOneById(UUID dietId);

    List<Diet> findByCreatedBy(User user);

    Diet findOneByCreatedByAndId(User user, UUID dietId);
}
