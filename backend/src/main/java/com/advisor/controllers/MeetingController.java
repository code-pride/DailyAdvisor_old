package com.advisor.controllers;

import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;
import com.advisor.service.MeetingService;
import com.advisor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(MeetingController.class);

    @RequestMapping(value = { "meeting/add" }, method = RequestMethod.POST)
    public ResponseEntity addMeeting(@Valid @RequestBody MeetingRequest meetingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        if(UUID.fromString(meetingRequest.getUserId2()) == user.getId()){
            logger.warn("Bad user request");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        else {
            try {
                meetingService.addMeeting(user, meetingRequest);
            } catch (EntityNotFoundException e){
                logger.warn(e.getStandardMessageCode());
                return new ResponseEntity(e.getStandardResponseCode());
            } catch (DataRepositoryException e) {
                return new ResponseEntity(e.getStandardResponseCode());
            }
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping(value = { "/meeting/{meetingId}" }, method = RequestMethod.GET)
    public ResponseEntity<MeetingResponse> getMeetingById(@PathVariable UUID meetingId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        MeetingResponse meetingResponse = meetingService.findMeetingByIdAndUser(meetingId, user);
        if(meetingResponse != null){
            return new ResponseEntity<>(meetingResponse, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "/meeting/" }, method = RequestMethod.GET)
    public ResponseEntity<List<MeetingResponse>> getMeetingByUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<MeetingResponse> meetingResponseList = meetingService.findMeetingByUser(user);
        if(meetingResponseList != null){
            return new ResponseEntity<>(meetingResponseList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = { "/meeting/{meetingId}/{statusChange}" }, method = RequestMethod.PUT)
    public ResponseEntity acceptMeeting(@PathVariable UUID meetingId, @PathVariable String statusChange) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try {
            switch (statusChange) {
                case ("accept"): {
                    meetingService.updateMeetingStatus(meetingId, user, "accept");
                    break;
                }
                case ("cancel"): {
                    meetingService.updateMeetingStatus(meetingId, user, "cancel");
                    break;
                }
                default:
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            meetingService.updateMeetingStatus(meetingId, user, "accept");
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = { "meeting/update" }, method = RequestMethod.PUT)
    public ResponseEntity updateMeeting(@Valid @RequestBody MeetingRequest meetingRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        try {
            meetingService.updateMeeting(meetingRequest, user);
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
