package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meeting")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meeting_id")
    private int meetingId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_meeting",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "meeting_id") }
    )
    @JoinColumn(name = "user_id")
    private User participantId;
    @Column(name = "meeting_text")
    private String meetingText;
    @Column(name = "creat_date")
    private Date createDate;
    @Column(name = "edit_date")
    private Date editDate;
//TODO gettery settery

}