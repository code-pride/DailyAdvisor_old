package com.advisor.service;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.repository.MeetingRepository;
import com.advisor.service.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("meetingService")
public class MeetingServiceImpl implements MeetingService {

    private static final String USER_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.user";

    private static final String MEETING_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String MEETING_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    @Autowired
    @Qualifier("meetingRepository")
    private MeetingRepository repository;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Override
    public Meeting create(Meeting meeting) throws EntityExists {
        if(meeting.getId() == null || !repository.existsById(meeting.getId())) {
            return repository.save(meeting);
        } else {
            throw new EntityExists(MEETING_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(MEETING_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Meeting> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Meeting> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Meeting update(Meeting meeting) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(meeting.getId())){
            return repository.save(meeting);
        } else {
            throw new EntityNotFoundException(MEETING_NOT_FOUND_MESSAGE_CODE);
        }
    }


    @Override
    public void addMeeting(User user, MeetingRequest meetingRequest) throws DataRepositoryException {
        Optional<User> user2 = userService.findById(meetingRequest.getUserId2());
        if(user2.isPresent()) {
            Event event = new Event(meetingRequest.getEventRequest());
            eventService.create(event);
            Meeting meeting = new Meeting(user, user2.get(), meetingRequest, event);
            repository.save(meeting);
        } else {
            throw new EntityNotFoundException(USER_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public MeetingResponse findMeetingByIdAndUser(UUID meetingId, User user) {
        Meeting meeting = repository.findMeetingById(meetingId);
        if(meeting != null && (meeting.getUserId().getId().equals(user.getId()) || meeting.getUserId2().getId().equals(user.getId()))){
            return new MeetingResponse(meeting);
        }
        return null;
    }

    @Override
    public List<MeetingResponse> findMeetingByUser(User user) {
        List<Meeting> meetingList = repository.findMeetingsByUserIdOrUserId2(user, user);
        List<MeetingResponse> meetingResponseList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            meetingResponseList.add(new MeetingResponse(meeting));
        }
        return meetingResponseList;
    }

    @Override
    public void updateMeetingStatus(UUID meetingId, User user, String newStatus) throws EntityNotFoundException {
        Optional<Meeting> meeting = repository.findById(meetingId);
        if(meeting.isPresent()){
            if(meeting.get().getUserId().equals(user)) {
                if (newStatus.equals("accept") && meeting.get().getStatus().equals("sent")) {
                    repository.updateMeeting(user, "accepted");
                }
                else if(newStatus.equals("cancel")){
                    repository.updateMeeting(user, "canceled");
                }
            }
        }
        throw new EntityNotFoundException(MEETING_NOT_FOUND_MESSAGE_CODE);

    }

    @Override
    public void updateMeeting(MeetingRequest meetingRequest, User user) throws DataRepositoryException {
        Meeting meeting = repository.findOneByIdAndUserId(meetingRequest.getMeetingId(), user);

        Event event = new Event(meetingRequest.getEventRequest());
        if (!(event.equals(meeting.getEvent()))) {
            event.setParentEvent(meeting.getEvent());
            eventService.create(event);
        }
        meeting.setLocation(meetingRequest.getLocation());
        meeting.setMeetingText(meetingRequest.getMeetingText());
        repository.updateMeeting(meeting);
    }
}
