package com.advisor.controller;

import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.model.response.UserProfileResponse;
import com.advisor.service.Exceptions.MeetingNotFoundException;
import com.advisor.service.MeetingService;
import com.advisor.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = { "meeting/add" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addMeeting(@RequestBody MeetingRequest meetingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        if(meetingRequest.getUserId2() == user.getId()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            meetingService.addMeeting(user, meetingRequest);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping(value = { "/meeting/{meetingId}" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<MeetingResponse> getMeetingById(@PathVariable Long meetingId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        MeetingResponse meetingResponse = meetingService.findMeetingByIdAndUser(meetingId, user);
        if(meetingResponse != null){ //TODO make exception catch
            return new ResponseEntity<>(meetingResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "/meeting/" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<MeetingResponse>> getMeetingByUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<MeetingResponse> meetingResponseList = meetingService.findMeetingByUser(user);
        if(meetingResponseList != null){ //TODO make exception catch
            return new ResponseEntity<>(meetingResponseList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "/meeting/{meetingId}/accept" }, method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity acceptMeeting(@PathVariable Long meetingId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try {
            meetingService.acceptMeeting(meetingId, user);
        } catch (MeetingNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = { "meeting/update" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateMeeting(@RequestBody MeetingRequest meetingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        try {
            meetingService.updateMeeting(meetingRequest, user);
        } catch (MeetingNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    //delete meeting
    //move meeting
    //set as done
    //update meeting
    //walidacja
}
