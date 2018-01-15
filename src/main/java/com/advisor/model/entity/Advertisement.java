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
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "adv_text")
    private String advText;
    @Column(name = "creat_date")
    private Date createDate;
    @Column(name = "edit_date")
    private Date editDate;
    @Column
    private int visits;
    @Column
    private int answers;

//TODO gettery settery

}