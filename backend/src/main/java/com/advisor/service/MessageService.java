package com.advisor.service;

import com.advisor.model.request.MessageRequest;
import com.advisor.model.entity.Message;
import com.advisor.model.entity.User;
import com.advisor.service.Exceptions.MessageNotFoundException;

import java.util.List;

public interface MessageService {

    void addUserMessage(User user, User user2, MessageRequest messageRequest);

    List<Message> getAllUserMessages(User user) throws MessageNotFoundException;

    List<Message> getUserMessages(User userId, User userId2) throws MessageNotFoundException;

}
