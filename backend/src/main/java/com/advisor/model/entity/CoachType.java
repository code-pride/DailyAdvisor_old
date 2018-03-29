package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "coach_type")
public class CoachType {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @Column(nullable = false, name = "type")
    private String type;

    public CoachType() {
    }

    public CoachType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoachType coachType = (CoachType) o;
        return Objects.equals(getType(), coachType.getType());
    }
}
