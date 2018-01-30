package com.advisor.controllers;

import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.request.CoachingRequest;
import com.advisor.model.response.CoachingResponse;
import com.advisor.model.response.UserProfileResponse;
import com.advisor.service.CoachService;
import com.advisor.service.Exceptions.CoachingNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "coaching/addClient" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addClient(@RequestBody CoachingRequest coachingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User client = userService.findUserById(coachingRequest.getClientId());
        try {
            coachService.findByCoachAndClient(user, client);
        }
        catch (CoachingNotFoundException e){
            coachService.addNewCoaching(user, client);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.IM_USED);
    }

    @RequestMapping(value = { "coaching/getClientCoaches" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CoachingResponse>> getClientCoaches()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Coaching> coachingList = coachService.findByClient(user);
        if(coachingList != null) {
            return new ResponseEntity(getCoachingResponsesByUser(coachingList, "client"), HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "coaching/getCoachClients" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CoachingResponse>> getCoachClients()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Coaching> coachingList = coachService.findByCoach(user);
        if(coachingList != null) {
            return new ResponseEntity(getCoachingResponsesByUser(coachingList, "coach"), HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "coaching/acceptCoaching" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity acceptCoaching(@RequestBody CoachingRequest coachingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User coach = userService.findUserById(coachingRequest.getCoachId());
        try {
            Coaching coaching = coachService.findByCoachAndClient(coach, user);
            if (coaching.getStatus().equals("sent")) {
                coaching.setStatus("accepted");
                coachService.updateStatus(coaching);
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (CoachingNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "coaching/cancelCoaching" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity leaveCoach(@RequestBody CoachingRequest coachingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        User coach = userService.findUserById(coachingRequest.getCoachId());
        try {
            Coaching coaching = coachService.findByCoachAndClient(coach, user);
            if (coaching.getStatus().equals("sent") || coaching.getStatus().equals("accepted")) {
                coaching.setStatus("canceled");
                coachService.updateStatus(coaching);
                return new ResponseEntity(HttpStatus.OK);
            }
            else{
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (CoachingNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    private List<CoachingResponse> getCoachingResponsesByUser (List<Coaching> coachingList, String user) {
        if (coachingList != null) {
            List<User> users = new ArrayList<>();
            if(user.equals("coach")){
                for (Coaching coaching : coachingList) {
                    users.add(userService.findUserById(coaching.getClient().getId()));
                }
            }else if(user.equals("client")){
                for (Coaching coaching : coachingList) {
                    users.add(userService.findUserById(coaching.getCoach().getId()));
                }
            }
            List<UserProfile> userProfileList = userService.findByUsers(users);
            List<CoachingResponse> coachingResponsesList = new ArrayList<>();
            int i = 0;
            for (UserProfile userProfile : userProfileList) {
                coachingResponsesList.add(new CoachingResponse(userProfile, coachingList.get(i).getStatus()));
                i++;
            }
            return coachingResponsesList;
        }
        return null;
    }

//    private List<UserProfileResponse> getUsersProfilesByUsers (List<Coaching> coachingList, String user) {
//        if (coachingList != null) {
//            List<User> users = new ArrayList<>();
//            if(user.equals("coach")){
//                for (Coaching coaching : coachingList) {
//                    users.add(userService.findUserById(coaching.getClient().getId()));
//                }
//            }else if(user.equals("client")){
//                for (Coaching coaching : coachingList) {
//                    users.add(userService.findUserById(coaching.getCoach().getId()));
//                }
//            }
//            List<UserProfile> userProfileList = userService.findByUsers(users);
//            List<UserProfileResponse> userProfileResponseList = new ArrayList<>();
//            for (UserProfile userProfile : userProfileList) {
//                userProfileResponseList.add(new UserProfileResponse(userProfile));
//            }
//            return userProfileResponseList;
//        }
//        return null;
//    }

}
