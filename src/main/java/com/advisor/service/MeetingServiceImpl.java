package com.advisor.service;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.repository.EventRepository;
import com.advisor.repository.MeetingRepository;
import com.advisor.service.Exceptions.MeetingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        eventRepository.save(event);
        Meeting meeting = new Meeting(user, user2, meetingRequest, event);
        meetingRepository.save(meeting);
    }

    @Override
    public MeetingResponse findMeetingByIdAndUser(Long meetingId, User user) {
        Meeting meeting = meetingRepository.findMeetingByMeetingId(meetingId);
        if(meeting != null && (meeting.getUserId().getId().equals(user.getId()) || meeting.getUserId2().getId().equals(user.getId()))){
            return new MeetingResponse(meeting);
        }
        return null;
    }

    @Override
    public List<MeetingResponse> findMeetingByUser(User user) {
        List<Meeting> meetingList = meetingRepository.findMeetingsByUserIdOrUserId2(user, user);
        List<MeetingResponse> meetingResponseList = new ArrayList<MeetingResponse>();
        for (Meeting meeting : meetingList) {
            meetingResponseList.add(new MeetingResponse(meeting));
        }
        return meetingResponseList;
    }

    @Override
    public void updateMeetingStatus(Long meetingId, User user, String newStatus) throws MeetingNotFoundException{
        Meeting meeting = meetingRepository.findMeetingByMeetingIdAndUserId2(meetingId, user);
        if(meeting != null ) {
            if (newStatus.equals("accept") && meeting.getStatus().equals("sent")) {
                meetingRepository.updateMeeting(user, "accepted");
            }
            else if(newStatus.equals("cancel")){
                meetingRepository.updateMeeting(user, "canceled");
            }
            else
                throw new MeetingNotFoundException();
        }
        else {
            throw new MeetingNotFoundException();
        }
    }

    @Override
    public void updateMeeting(MeetingRequest meetingRequest, User user) throws MeetingNotFoundException {
        List<Meeting> meetingList = meetingRepository.findMeetingByMeetingIdAndUserId(meetingRequest.getMeetingId(), user);

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
        throw new MeetingNotFoundException();
    }
}
