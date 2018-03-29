package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class RecurringType {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @Column(nullable = false, name = "recurring_name", unique = true)
    private String recurringName;


    public RecurringType() {
    }

    public RecurringType(String recurringName) {
        this.recurringName = recurringName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecurringType that = (RecurringType) o;
        return Objects.equals(getRecurringName(), that.getRecurringName());
    }
}
