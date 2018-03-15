package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.service.Exceptions.MeetingNotFoundException;
import com.advisor.service.Exceptions.UserNotFoundException;

import java.util.List;

public interface MeetingService {

    void addMeeting(User user, MeetingRequest meetingRequest)throws UserNotFoundException;

    MeetingResponse findMeetingByIdAndUser(Long meetingId, User user);

    List<MeetingResponse> findMeetingByUser(User user);

    void updateMeetingStatus(Long meetingId, User user, String newStatus) throws MeetingNotFoundException;

    void updateMeeting(MeetingRequest meetingRequest, User user) throws MeetingNotFoundException;

}
