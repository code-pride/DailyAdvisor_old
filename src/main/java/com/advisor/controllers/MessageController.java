package com.advisor.controllers;

import com.advisor.model.request.MessageRequest;
import com.advisor.model.entity.Message;
import com.advisor.model.entity.User;
import com.advisor.model.response.MessageResponse;
import com.advisor.service.MessageService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "message/add" }, method = RequestMethod.POST)
    public ResponseEntity addUserMessage(@Valid @RequestBody MessageRequest messageRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User user2 = userService.findUserById(messageRequest.getReceiverId());

        messageService.addUserMessage(user, user2, messageRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = { "message/{userId}" }, method = RequestMethod.GET)
    public ResponseEntity<List<MessageResponse>> getUserMessages(@PathVariable long userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User user2 = userService.findUserById(userId);

        List<Message> messages = messageService.getUserMessages(user, user2);
        List<MessageResponse> messageResponses = new ArrayList<>();
        messages.forEach(message->messageResponses.add(new MessageResponse(message)));

        return new ResponseEntity<>(messageResponses, HttpStatus.OK);
    }

    @RequestMapping(value = { "message/" }, method = RequestMethod.GET)
    public ResponseEntity<List<MessageResponse>> getAllUserMessages() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        List<Message> messages = messageService.getAllUserMessages(user);
        List<MessageResponse> messageResponses = new ArrayList<>();
        messages.forEach(message->messageResponses.add(new MessageResponse(message)));

        return new ResponseEntity<>(messageResponses, HttpStatus.OK);
    }

}