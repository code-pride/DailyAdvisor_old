package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adv_id")
    private Long advId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user")
    private User user;

    @Column(nullable = false, name = "adv_text")
    private String advText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date create_date = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="edit_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private Date edit_date = new Date();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "adv_coach_types", joinColumns = @JoinColumn(name = "adv_id"), inverseJoinColumns = @JoinColumn(name = "coach_type_id"))
    private Set<CoachType> coachTypes;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int visits;

    @Column(nullable = false)
    private int answers;


    public Advertisement() {
    }

    public Advertisement(User user, String advText) {
        this.user = user;
        this.advText = advText;
        this.visits = 0;
        this.answers = 0;
        this.status = "disabled";
    }

    public Long getAdvId() {
        return advId;
    }

    public void setAdvId(Long advId) {
        this.advId = advId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAdvText() {
        return advText;
    }

    public void setAdvText(String advText) {
        this.advText = advText;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getEdit_date() {
        return edit_date;
    }

    public void setEdit_date(Date edit_date) {
        this.edit_date = edit_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}