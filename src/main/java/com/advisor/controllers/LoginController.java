package com.advisor.controllers;

import javax.validation.Valid;
import com.advisor.model.entity.User;
import com.advisor.model.request.NewUserRequest;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity createNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
		User userExists = userService.findUserByEmail(newUserRequest.getEmail());
		if (userExists == null) {
            userService.saveUser(newUserRequest);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
		}
	}

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public ResponseEntity afterLogin() {
        return new ResponseEntity(HttpStatus.OK);
    }


}
