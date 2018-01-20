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
    private User userId;

    @Column(nullable = false, name = "train_text")
    private String trainText;

//TODO gettery settery

}