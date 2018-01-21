package com.advisor.service;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.repository.EventRepository;
import com.advisor.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

    @Autowired
    @Qualifier("meetingRepository")
    private MeetingRepository meetingRepository;

    @Autowired
    private UserService userService;

    @Override
    public void addMeeting(User user, MeetingRequest meetingRequest) {
        Event event = new Event(meetingRequest.getEventRequest());

        User user2 = userService.findUserById(meetingRequest.getUserId2());
        event.setCreatedBy(user);
        event.setUserEvent(user);
        eventRepository.save(event);
        Meeting meeting = new Meeting(user, user2, meetingRequest, event);
        meetingRepository.save(meeting);

    }
}
