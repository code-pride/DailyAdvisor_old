package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;

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

}