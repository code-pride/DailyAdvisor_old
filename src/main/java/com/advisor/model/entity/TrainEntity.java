package com.advisor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "train", schema = "daily_advisor", catalog = "")
public class TrainEntity {
    private int trainId;
    private Timestamp createDate;
    private Timestamp editDate;
    private String trainText;
    private Collection<CalendarEntity> calendarsByTrainId;
    private UserEntity userByAuthorId;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "train_id")
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
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
    @Column(name = "train_text")
    public String getTrainText() {
        return trainText;
    }

    public void setTrainText(String trainText) {
        this.trainText = trainText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainEntity that = (TrainEntity) o;

        if (trainId != that.trainId) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (editDate != null ? !editDate.equals(that.editDate) : that.editDate != null) return false;
        if (trainText != null ? !trainText.equals(that.trainText) : that.trainText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = trainId;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (editDate != null ? editDate.hashCode() : 0);
        result = 31 * result + (trainText != null ? trainText.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "trainByTrainId")
    public Collection<CalendarEntity> getCalendarsByTrainId() {
        return calendarsByTrainId;
    }

    public void setCalendarsByTrainId(Collection<CalendarEntity> calendarsByTrainId) {
        this.calendarsByTrainId = calendarsByTrainId;
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
