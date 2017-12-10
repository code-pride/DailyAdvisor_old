package com.advisor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "calendar", schema = "daily_advisor", catalog = "")
public class CalendarEntity {
    private int calendarId;
    private String comment;
    private Timestamp endDate;
    private String message;
    private Timestamp startDate;
    private String status;
    private String type;
    private DietEntity dietByDietId;
    private MeetingEntity meetingByMeetingId;
    private TrainEntity trainByTrainId;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "calendar_id")
    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEntity that = (CalendarEntity) o;

        if (calendarId != that.calendarId) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = calendarId;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "diet_id", referencedColumnName = "diet_id")
    public DietEntity getDietByDietId() {
        return dietByDietId;
    }

    public void setDietByDietId(DietEntity dietByDietId) {
        this.dietByDietId = dietByDietId;
    }

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "meeting_id")
    public MeetingEntity getMeetingByMeetingId() {
        return meetingByMeetingId;
    }

    public void setMeetingByMeetingId(MeetingEntity meetingByMeetingId) {
        this.meetingByMeetingId = meetingByMeetingId;
    }

    @ManyToOne
    @JoinColumn(name = "train_id", referencedColumnName = "train_id")
    public TrainEntity getTrainByTrainId() {
        return trainByTrainId;
    }

    public void setTrainByTrainId(TrainEntity trainByTrainId) {
        this.trainByTrainId = trainByTrainId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
