package com.advisor.repository;

import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findById(Long userId);

//    @Transactional
//    @Modifying
//    @Query("update User u set u.name = :name, u.lastName = :lastName where u.id = :userId")
//    void updateUser(@Param("userId") Long userId, @Param("name") String name, @Param("lastName")  String lastName);
}
