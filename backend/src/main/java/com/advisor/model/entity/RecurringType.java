package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class RecurringType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "recurring_name", unique = true)
    private String recurringName;


    public RecurringType() {
    }

    public RecurringType(String recurringName) {
        this.recurringName = recurringName;
    }

}
