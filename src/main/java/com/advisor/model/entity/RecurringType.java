package com.advisor.model.entity;

import javax.persistence.*;

@Entity
public class RecurringType {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="recurring_type_id")
    private RecurringPattern recurringPattern;
}
