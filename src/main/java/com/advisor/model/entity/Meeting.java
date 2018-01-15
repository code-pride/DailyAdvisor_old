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
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId;
    @ManyToOne(cascade = CascadeType.ALL)
    private User userId2;
    @Column(name = "meeting_text")
    private String meetingText;
    @Column(name = "creat_date")
    private Date createDate;
    @Column(name = "edit_date")
    private Date editDate;

//TODO gettery settery

}