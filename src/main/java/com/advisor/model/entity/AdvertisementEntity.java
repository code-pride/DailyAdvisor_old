package com.advisor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "advertisement", schema = "daily_advisor", catalog = "")
public class AdvertisementEntity {
    private int advId;
    private String advText;
    private Integer answers;
    private Timestamp creatDate;
    private Timestamp editDate;
    private Integer visits;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "adv_id")
    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
    }

    @Basic
    @Column(name = "adv_text")
    public String getAdvText() {
        return advText;
    }

    public void setAdvText(String advText) {
        this.advText = advText;
    }

    @Basic
    @Column(name = "answers")
    public Integer getAnswers() {
        return answers;
    }

    public void setAnswers(Integer answers) {
        this.answers = answers;
    }

    @Basic
    @Column(name = "creat_date")
    public Timestamp getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Timestamp creatDate) {
        this.creatDate = creatDate;
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
    @Column(name = "visits")
    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvertisementEntity that = (AdvertisementEntity) o;

        if (advId != that.advId) return false;
        if (advText != null ? !advText.equals(that.advText) : that.advText != null) return false;
        if (answers != null ? !answers.equals(that.answers) : that.answers != null) return false;
        if (creatDate != null ? !creatDate.equals(that.creatDate) : that.creatDate != null) return false;
        if (editDate != null ? !editDate.equals(that.editDate) : that.editDate != null) return false;
        if (visits != null ? !visits.equals(that.visits) : that.visits != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = advId;
        result = 31 * result + (advText != null ? advText.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        result = 31 * result + (creatDate != null ? creatDate.hashCode() : 0);
        result = 31 * result + (editDate != null ? editDate.hashCode() : 0);
        result = 31 * result + (visits != null ? visits.hashCode() : 0);
        return result;
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
