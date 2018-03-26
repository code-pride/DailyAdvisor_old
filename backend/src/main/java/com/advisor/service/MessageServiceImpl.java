package com.advisor.service;

import com.advisor.model.request.MessageRequest;
import com.advisor.model.entity.Message;
import com.advisor.model.entity.User;
import com.advisor.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Autowired
    @Qualifier("messageRepository")
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAllUserMessages(User user){
        return messageRepository.findAllUserMessages(user);
    }

    @Override
    public List<Message> getUserMessages(User userId, User userId2){
        return messageRepository.findBySenderAndReceiver(userId, userId2);
    }

    @Override
    public void addUserMessage(User user, User user2, MessageRequest messageRequest) {
        messageRepository.save(new Message(user, user2, messageRequest.getMsgTime(), messageRequest.getMessage()));
    }

}
