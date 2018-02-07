package com.advisor.model.entity;

import javax.persistence.*;

@Entity
public class RecurringPattern {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column (nullable = false, name = "id")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeparation_count() {
        return separation_count;
    }

    public void setSeparation_count(Integer separation_count) {
        this.separation_count = separation_count;
    }

    public Integer getMax_num_of_occurrences() {
        return max_num_of_occurrences;
    }

    public void setMax_num_of_occurrences(Integer max_num_of_occurrences) {
        this.max_num_of_occurrences = max_num_of_occurrences;
    }

    public Integer getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(Integer day_of_week) {
        this.day_of_week = day_of_week;
    }

    public Integer getWeek_of_month() {
        return week_of_month;
    }

    public void setWeek_of_month(Integer week_of_month) {
        this.week_of_month = week_of_month;
    }

    public Integer getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(Integer day_of_month) {
        this.day_of_month = day_of_month;
    }

    public Integer getMonth_of_year() {
        return month_of_year;
    }

    public void setMonth_of_year(Integer month_of_year) {
        this.month_of_year = month_of_year;
    }

    public RecurringType getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(RecurringType recurringType) {
        this.recurringType = recurringType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurringPattern that = (RecurringPattern) o;

        if (getSeparation_count() != null ? !getSeparation_count().equals(that.getSeparation_count()) : that.getSeparation_count() != null)
            return false;
        if (getMax_num_of_occurrences() != null ? !getMax_num_of_occurrences().equals(that.getMax_num_of_occurrences()) : that.getMax_num_of_occurrences() != null)
            return false;
        if (getDay_of_week() != null ? !getDay_of_week().equals(that.getDay_of_week()) : that.getDay_of_week() != null)
            return false;
        if (getWeek_of_month() != null ? !getWeek_of_month().equals(that.getWeek_of_month()) : that.getWeek_of_month() != null)
            return false;
        if (getDay_of_month() != null ? !getDay_of_month().equals(that.getDay_of_month()) : that.getDay_of_month() != null)
            return false;
        if (getMonth_of_year() != null ? !getMonth_of_year().equals(that.getMonth_of_year()) : that.getMonth_of_year() != null)
            return false;
        return getRecurringType() != null ? getRecurringType().equals(that.getRecurringType()) : that.getRecurringType() == null;
    }

    @Override
    public int hashCode() {
        int result = getSeparation_count() != null ? getSeparation_count().hashCode() : 0;
        result = 31 * result + (getMax_num_of_occurrences() != null ? getMax_num_of_occurrences().hashCode() : 0);
        result = 31 * result + (getDay_of_week() != null ? getDay_of_week().hashCode() : 0);
        result = 31 * result + (getWeek_of_month() != null ? getWeek_of_month().hashCode() : 0);
        result = 31 * result + (getDay_of_month() != null ? getDay_of_month().hashCode() : 0);
        result = 31 * result + (getMonth_of_year() != null ? getMonth_of_year().hashCode() : 0);
        result = 31 * result + (getRecurringType() != null ? getRecurringType().hashCode() : 0);
        return result;
    }
}
