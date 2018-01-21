package com.advisor.controller;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.Location;
import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.service.MeetingService;
import com.advisor.service.UserService;
import com.google.gson.Gson;
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

import java.sql.Time;
import java.util.Date;

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

        Event event = new Event();
        event.setUserEvent(user);
        event.setCreateDate(new Date());
        event.setEndTime(new Time(43200));
        event.setFullDayEvent(false);
        MeetingRequest meeting = new MeetingRequest();
        meeting.setLocation(new Location(50.243788, 50.243788));
        meeting.setUserId2(new Long(2));
        Gson gson = new Gson();
        String json = gson.toJson(event);
        meetingService.addMeeting(user, meetingRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

}
