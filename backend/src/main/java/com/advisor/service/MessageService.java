package com.advisor.service;

import com.advisor.model.request.MessageRequest;
import com.advisor.model.entity.Message;
import com.advisor.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface MessageService extends IService<Message, UUID> {

    void addUserMessage(User user, User user2, MessageRequest messageRequest);

    List<Message> getAllUserMessages(User user);

    List<Message> getUserMessages(User userId, User userId2);

}
