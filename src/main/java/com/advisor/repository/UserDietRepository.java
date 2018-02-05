package com.advisor.repository;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDietRepository")
public interface UserDietRepository extends JpaRepository<UserDiet, Integer> {

    @Query("SELECT u FROM UserDiet u WHERE u.diet = :diet AND u.user = :user")
    List<UserDiet> findUserDietByUserAndId(@Param("user") User user, @Param("diet") Diet diet);


}
