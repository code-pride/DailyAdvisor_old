package com.advisor.service;

import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.response.MeetingResponse;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface MeetingService extends IService<Meeting, UUID> {

    void addMeeting(User user, MeetingRequest meetingRequest) throws DataRepositoryException;

    MeetingResponse findMeetingByIdAndUser(UUID meetingId, User user);

    List<MeetingResponse> findMeetingByUser(User user);

    void updateMeetingStatus(UUID meetingId, User user, String newStatus) throws EntityNotFoundException;

    void updateMeeting(MeetingRequest meetingRequest, User user) throws DataRepositoryException;

}
