package com.advisor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "meeting", schema = "daily_advisor", catalog = "")
public class MeetingEntity {
    private int meetingId;
    private Timestamp createDate;
    private Timestamp editDate;
    private String meetingText;
    private Collection<CalendarEntity> calendarsByMeetingId;
    private UserEntity userByAuthorId;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "meeting_id")
    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "edit_date")
    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "meeting_text")
    public String getMeetingText() {
        return meetingText;
    }

    public void setMeetingText(String meetingText) {
        this.meetingText = meetingText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeetingEntity that = (MeetingEntity) o;

        if (meetingId != that.meetingId) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (editDate != null ? !editDate.equals(that.editDate) : that.editDate != null) return false;
        if (meetingText != null ? !meetingText.equals(that.meetingText) : that.meetingText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = meetingId;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (editDate != null ? editDate.hashCode() : 0);
        result = 31 * result + (meetingText != null ? meetingText.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "meetingByMeetingId")
    public Collection<CalendarEntity> getCalendarsByMeetingId() {
        return calendarsByMeetingId;
    }

    public void setCalendarsByMeetingId(Collection<CalendarEntity> calendarsByMeetingId) {
        this.calendarsByMeetingId = calendarsByMeetingId;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByAuthorId() {
        return userByAuthorId;
    }

    public void setUserByAuthorId(UserEntity userByAuthorId) {
        this.userByAuthorId = userByAuthorId;
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
