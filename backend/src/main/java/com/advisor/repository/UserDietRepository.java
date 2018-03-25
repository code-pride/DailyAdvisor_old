package com.advisor.repository;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("userDietRepository")
public interface UserDietRepository extends JpaRepository<UserDiet, UUID> {

    UserDiet findOneByUserAndDiet(User user, Diet diet);

    List<UserDiet> findByUser(User user);
}
