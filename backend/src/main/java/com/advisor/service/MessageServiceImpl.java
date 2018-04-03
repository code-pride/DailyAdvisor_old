package com.advisor.service;

import com.advisor.model.request.MessageRequest;
import com.advisor.model.entity.Message;
import com.advisor.model.entity.User;
import com.advisor.repository.MessageRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    private static final String MESSAGE_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String MESSAGE_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    @Autowired
    @Qualifier("messageRepository")
    private MessageRepository repository;

    @Override
    public Message create(Message message) throws EntityExists {
        if(message.getId() == null || !repository.existsById(message.getId())) {
            return repository.save(message);
        } else {
            throw new EntityExists(MESSAGE_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(MESSAGE_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Message> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Message update(Message message) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(message.getId())){
            return repository.save(message);
        } else {
            throw new EntityNotFoundException(MESSAGE_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Message> getAllUserMessages(User user){
        return repository.findAllUserMessages(user);
    }

    //TODO remove hsql
    @Override
    public List<Message> getUserMessages(User userId, User userId2){
        return repository.findBySenderAndReceiver(userId, userId2);
    }

    //move creating Message to controller
    @Override
    public void addUserMessage(User user, User user2, MessageRequest messageRequest) {
        repository.save(new Message(user, user2, messageRequest.getMsgTime(), messageRequest.getMessage()));
    }

}
