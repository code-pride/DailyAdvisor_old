package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "coach_type")
public class CoachType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, name = "type")
    private String type;

    public CoachType() {
    }

    public CoachType(String type) {
        this.type = type;
    }

}
