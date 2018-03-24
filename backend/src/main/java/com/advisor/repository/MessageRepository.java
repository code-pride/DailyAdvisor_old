package com.advisor.repository;


import com.advisor.model.entity.Message;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("messageRepository")
public interface MessageRepository extends SimplyRepository<Message> {

    @Query("SELECT m FROM Message m WHERE m.sender = :user OR m.receiver = :user")
    List<Message> findAllUserMessages(@Param("user") User user);

    List<Message> findBySenderAndReceiver(User userId, User userId2);

}
