package com.advisor.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "coach_type")
public class CoachType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, name = "type")
    private String type;
}
