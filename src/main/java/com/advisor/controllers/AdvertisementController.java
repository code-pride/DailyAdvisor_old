package com.advisor.controllers;

import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.response.AdvertisementResponse;
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
        if (advertisementService.getAdvertisementByUser(user) == null){
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

    @RequestMapping(value = { "advertisement/get/{userId}" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<AdvertisementResponse> getAdvertisementByUserId(@PathVariable Long userId)
    {
        User user = userService.findUserById(userId);

        if(user != null){ //TODO make exception catch
            AdvertisementResponse advertisementResponse = advertisementService.getAdvertisementByUser(user);
            if(advertisementResponse != null){
                return new ResponseEntity(advertisementResponse, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = { "/advertisement/update" }, method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity updateAdvertisement(@RequestBody AdvertisementRequest advertisementRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        advertisementService.updateAdvertisement(user, advertisementRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

}
