package com.advisor.model.entity;

import javax.persistence.*;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecurringName() {
        return recurringName;
    }

    public void setRecurringName(String recurringName) {
        this.recurringName = recurringName;
    }
}
