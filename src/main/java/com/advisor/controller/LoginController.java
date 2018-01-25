package com.advisor.controller;

import javax.validation.Valid;
import com.advisor.model.entity.User;
import com.advisor.model.request.NewUserRequest;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity createNewUser(@RequestBody @Valid NewUserRequest newUserRequest) {
		User userExists = userService.findUserByEmail(newUserRequest.getEmail());
		if (userExists == null) {
            userService.saveUser(newUserRequest);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
		}
	}

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity afterLogin() {
        return new ResponseEntity(HttpStatus.OK);
    }


}
