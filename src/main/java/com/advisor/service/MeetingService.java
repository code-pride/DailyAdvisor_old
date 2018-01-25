package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.service.Exceptions.MeetingNotFoundException;

import java.util.List;

public interface MeetingService {

    void addMeeting(User user, MeetingRequest meetingRequest);

    MeetingResponse findMeetingByIdAndUser(Long meetingId, User user);

    List<MeetingResponse> findMeetingByUser(User user);

    void acceptMeeting(Long meetingId, User user) throws MeetingNotFoundException;

    void updateMeeting(MeetingRequest meetingRequest, User user) throws MeetingNotFoundException;
}
