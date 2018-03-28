package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_")
    private User user;

    @Column(nullable = false, name = "adv_text")
    private String advText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date create_date = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="edit_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date edit_date = new Date();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "adv_coach_types", joinColumns = @JoinColumn(name = "adv_id"), inverseJoinColumns = @JoinColumn(name = "coach_type_id"))
    private CoachType coachType;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int visits;

    @Column(nullable = false)
    private int answers;


    public Advertisement() {
    }

    public Advertisement(User user, String advText, CoachType coachType) {
        this.user = user;
        this.advText = advText;
        this.visits = 0;
        this.answers = 0;
        this.status = "disabled";
        this.coachType = coachType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return getVisits() == that.getVisits() &&
                getAnswers() == that.getAnswers() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getAdvText(), that.getAdvText()) &&
                Objects.equals(getCreate_date(), that.getCreate_date()) &&
                Objects.equals(getEdit_date(), that.getEdit_date()) &&
                Objects.equals(getCoachType(), that.getCoachType()) &&
                Objects.equals(getStatus(), that.getStatus());
    }

}