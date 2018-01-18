package com.advisor.controller;

import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.service.AdvertisementService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdvertisementController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdvertisementService advertisementService;

    @RequestMapping(value = { "advertisement/add" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addAdvertisement(@RequestBody AdvertisementRequest advertisementRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        //TODO check if adv exists
        advertisementService.setAdvertisement(user, advertisementRequest);

        return new ResponseEntity(HttpStatus.OK);
    }
    //create
    //get
    //getall
    //update
//    wylaczanie ogloszenia
//    usuwanie ogloszenia
//    update ogloszenia
//    zliczanie wejsc
}
