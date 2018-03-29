package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class RecurringPattern {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @Column (nullable = false, name = "separation_count")
    private Integer separation_count;

    @Column (nullable = false, name = "max_num_of_occurrences")
    private Integer max_num_of_occurrences;

    @Column (name = "day_of_week")
    private Integer day_of_week;

    @Column (name = "week_of_month")
    private Integer week_of_month;

    @Column (name = "day_of_month")
    private Integer day_of_month;

    @Column (name = "month_of_year")
    private Integer month_of_year;

    @ManyToOne(cascade=CascadeType.ALL)
    private RecurringType recurringType;


    public RecurringPattern() {
    }

    public RecurringPattern(Integer separation_count, Integer max_num_of_occurrences, Integer day_of_week, Integer week_of_month, Integer day_of_month, Integer month_of_year, RecurringType recurringType) {
        this.separation_count = separation_count;
        this.max_num_of_occurrences = max_num_of_occurrences;
        this.day_of_week = day_of_week;
        this.week_of_month = week_of_month;
        this.day_of_month = day_of_month;
        this.month_of_year = month_of_year;
        this.recurringType = recurringType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecurringPattern that = (RecurringPattern) o;
        return Objects.equals(getSeparation_count(), that.getSeparation_count()) &&
                Objects.equals(getMax_num_of_occurrences(), that.getMax_num_of_occurrences()) &&
                Objects.equals(getDay_of_week(), that.getDay_of_week()) &&
                Objects.equals(getWeek_of_month(), that.getWeek_of_month()) &&
                Objects.equals(getDay_of_month(), that.getDay_of_month()) &&
                Objects.equals(getMonth_of_year(), that.getMonth_of_year()) &&
                Objects.equals(getRecurringType(), that.getRecurringType());
    }
}
