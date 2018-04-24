package com.advisor.controllers;

import javax.validation.Valid;

import com.advisor.Events.OnRegistrationCompleteEvent;
import com.advisor.model.entity.User;
import com.advisor.model.request.NewUserRequest;
import com.advisor.model.request.RegistrationConfirmRequest;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    public SimpleMailMessage mailMessage;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity createNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
        if (userService.findUserByEmail(newUserRequest.getEmail()) == null) {
            if ("client".equals(newUserRequest.getUserType())) {
                userService.registerClient(newUserRequest);
                return new ResponseEntity(HttpStatus.OK);
            } else if ("coach".equals(newUserRequest.getUserType())) {
                userService.registerCoach(newUserRequest);
                User user = userService.findUserByEmail(newUserRequest.getEmail());
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user));

                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
        }
    }

    @RequestMapping(value = "/registrationConfirm", method = RequestMethod.POST)
    public ResponseEntity confirmRegistration(@RequestBody RegistrationConfirmRequest registrationConfirmRequest) {
        try {
            userService.confirmRegistration(registrationConfirmRequest.getToken());
        } catch (DataRepositoryException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //TODO to delete
    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public ResponseEntity afterLogin() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity hello() {
        return new ResponseEntity(HttpStatus.OK);
    }

}
