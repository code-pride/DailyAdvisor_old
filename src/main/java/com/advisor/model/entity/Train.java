package com.advisor.model.entity;

import javax.persistence.*;


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

//TODO gettery settery

}