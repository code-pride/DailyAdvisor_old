package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "train_id")
    private int trainId;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
    @Column(name = "train_text")
    private String trainText;
    @Column(name = "creat_date")
    private Date createDate;
    @Column(name = "edit_date")
    private Date editDate;
//TODO gettery settery

}