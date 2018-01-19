package com.advisor.controller;

import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.request.UserProfileRequest;
import com.advisor.model.responseClasses.AdvertisementResponse;
import com.advisor.model.responseClasses.UserProfileResponse;
import com.advisor.service.AdvertisementService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (advertisementService.findAdvertisementByUser(user) == null){
            advertisementService.setAdvertisement(user, advertisementRequest);

            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
        }
    }

    @RequestMapping(value = { "advertisement/get" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<AdvertisementResponse>> getAllAdvertisement()
    {
        List<AdvertisementResponse> advertisementList = advertisementService.selectAll();
        if (advertisementList != null){
            return new ResponseEntity(advertisementList, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


    //get

    //update
//    wylaczanie ogloszenia
//    usuwanie ogloszenia
//    update ogloszenia
//    zliczanie wejsc
}
