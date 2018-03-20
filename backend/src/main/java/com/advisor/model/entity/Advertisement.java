package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adv_id")
    private Long id;

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
}