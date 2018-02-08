package com.advisor.controllers;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.response.*;
import com.advisor.service.DietService;
import com.advisor.service.MeetingService;
import com.advisor.service.TrainService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CalendarController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private DietService dietService;

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = { "/calendar/" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<CalendarResponse> getMeetingByUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<MeetingResponse> meetingResponseList = meetingService.findMeetingByUser(user);

        List<Train> trainList = trainService.getAllActiveTrainings(user);
        List<TrainResponse> trainResponses = new ArrayList<>();
        for (Train train : trainList) {
            trainResponses.add(new TrainResponse(train));
        }

        List<Diet> diets = dietService.getAllActiveDiets(user);
        List<DietResponse> dietResponses = new ArrayList<>();
        for (Diet diet : diets) {
            dietResponses.add(new DietResponse(diet));
        }

        CalendarResponse calendarResponse = new CalendarResponse(meetingResponseList, trainResponses, dietResponses);

        if(calendarResponse != null){
            return new ResponseEntity<>(calendarResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
