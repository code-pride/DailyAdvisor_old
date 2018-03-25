package com.advisor.service;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.repository.EventRepository;
import com.advisor.repository.MeetingRepository;
import com.advisor.service.Exceptions.EntityNotFoundException;
import com.advisor.service.Exceptions.MeetingNotFoundException;
import com.advisor.service.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {

    private static final String MEETING_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meeting";
    private static final String USER_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.user";

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

    @Autowired
    @Qualifier("meetingRepository")
    private MeetingRepository meetingRepository;

    @Autowired
    private UserService userService;

    @Override
    public void addMeeting(User user, MeetingRequest meetingRequest) throws UserNotFoundException, EntityNotFoundException {
        Optional<User> user2 = userService.findById(meetingRequest.getUserId2());
        if(user2.isPresent()) {
            Event event = new Event(meetingRequest.getEventRequest());
            eventRepository.save(event);
            Meeting meeting = new Meeting(user, user2.get(), meetingRequest, event);
            meetingRepository.save(meeting);
        } else {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public MeetingResponse findMeetingByIdAndUser(UUID meetingId, User user) {
        Meeting meeting = meetingRepository.findMeetingById(meetingId);
        if(meeting != null && (meeting.getUserId().getId().equals(user.getId()) || meeting.getUserId2().getId().equals(user.getId()))){
            return new MeetingResponse(meeting);
        }
        return null;
    }

    @Override
    public List<MeetingResponse> findMeetingByUser(User user) {
        List<Meeting> meetingList = meetingRepository.findMeetingsByUserIdOrUserId2(user, user);
        List<MeetingResponse> meetingResponseList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            meetingResponseList.add(new MeetingResponse(meeting));
        }
        return meetingResponseList;
    }

    @Override
    public void updateMeetingStatus(UUID meetingId, User user, String newStatus) throws MeetingNotFoundException{

        Optional<Meeting> meeting = meetingRepository.findById(meetingId);
        if(meeting.isPresent()){
            if(meeting.get().getUserId().equals(user)) {
                if (newStatus.equals("accept") && meeting.get().getStatus().equals("sent")) {
                    meetingRepository.updateMeeting(user, "accepted");
                }
                else if(newStatus.equals("cancel")){
                    meetingRepository.updateMeeting(user, "canceled");
                }
            }
            else {
                throw new MeetingNotFoundException();
            }
        } else {
            throw new MeetingNotFoundException();
        }
    }

    @Override
    public void updateMeeting(MeetingRequest meetingRequest, User user) throws MeetingNotFoundException {
        List<Meeting> meetingList = meetingRepository.findMeetingByIdAndUserId(meetingRequest.getMeetingId(), user);

        if(meetingList != null && meetingList.size() == 1){
            Meeting meeting = meetingList.get(0);
            Event event = new Event(meetingRequest.getEventRequest());
            if (!(event.equals(meeting.getEvent()))){
                event.setParentEvent(meeting.getEvent());
                eventRepository.save(event);
            }
            meeting.setLocation(meetingRequest.getLocation());
            meeting.setMeetingText(meetingRequest.getMeetingText());
            meetingRepository.updateMeeting(meeting);
        }
        else {
            throw new MeetingNotFoundException();
        }
    }
}
