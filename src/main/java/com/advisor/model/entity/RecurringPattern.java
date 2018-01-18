package com.advisor.model.entity;

import javax.persistence.*;

@Entity
public class RecurringPattern {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Event event;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column (nullable = false, name = "recurring_type_id")
    private Long recurring_type_id;
    @Column (nullable = false, name = "separation_count")
    private int separation_count;
    @Column (nullable = false, name = "max_num_of_occurances")
    private int max_num_of_occurances;
    @Column (name = "day_of_week")
    private int day_of_week;
    @Column (name = "week_of_month")
    private int week_of_month;
    @Column (name = "day_of_month")
    private int day_of_month;
    @Column (name = "month_of_year")
    private int month_of_year;

}
