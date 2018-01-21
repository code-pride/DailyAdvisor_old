package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.MeetingRequest;

public interface MeetingService {

    void addMeeting(User user, MeetingRequest meetingRequest);
}
