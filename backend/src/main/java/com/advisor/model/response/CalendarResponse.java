package com.advisor.model.response;

import lombok.Data;

import java.util.List;

@Data
public class CalendarResponse {
    private List<MeetingResponse> meetingResponses;
    private List<TrainResponse> trainResponses;
    private List<DietResponse> dietResponses;

    public CalendarResponse() {
    }

    public CalendarResponse(List<MeetingResponse> meetingResponses, List<TrainResponse> trainResponses, List<DietResponse> dietResponses) {
        this.meetingResponses = meetingResponses;
        this.trainResponses = trainResponses;
        this.dietResponses = dietResponses;
    }

    public List<MeetingResponse> getMeetingResponses() {
        return meetingResponses;
    }

    public void setMeetingResponses(List<MeetingResponse> meetingResponses) {
        this.meetingResponses = meetingResponses;
    }

}
