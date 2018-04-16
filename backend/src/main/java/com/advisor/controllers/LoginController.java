package com.advisor.controllers;

import javax.validation.Valid;

import com.advisor.mail.EmailService;
import com.advisor.model.entity.User;
import com.advisor.model.request.NewUserRequest;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    public SimpleMailMessage template;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity createNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
		User user = userService.findUserByEmail(newUserRequest.getEmail());
		if (user == null) {
		    if("client".equals(newUserRequest.getUserType())){
                userService.registerClient(newUserRequest);
                return new ResponseEntity(HttpStatus.OK);
            } else if ("coach".equals(newUserRequest.getUserType())){
                userService.registerCoach(newUserRequest);

                String text = template.getText();
                emailService.sendSimpleMessage("swietoszeczek@gmail.com", "test_DA", text);

                return new ResponseEntity(HttpStatus.OK);
            }
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
		}
	}

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public ResponseEntity afterLogin() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity hello() {
        return new ResponseEntity(HttpStatus.OK);
    }



}
