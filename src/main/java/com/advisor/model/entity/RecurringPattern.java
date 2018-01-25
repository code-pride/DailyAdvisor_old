package com.advisor.model.entity;

import javax.persistence.*;

@Entity
public class RecurringPattern {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column (nullable = false, name = "recurring_type_id")
    private Long recurring_type_id;

    @Column (nullable = false, name = "separation_count")
    private int separation_count;

    @Column (nullable = false, name = "max_num_of_occurrences")
    private int max_num_of_occurrences;

    @Column (name = "day_of_week")
    private int day_of_week;

    @Column (name = "week_of_month")
    private int week_of_month;

    @Column (name = "day_of_month")
    private int day_of_month;

    @Column (name = "month_of_year")
    private int month_of_year;

    public RecurringPattern() {
    }

    public RecurringPattern(int separation_count, int max_num_of_occurrences, int day_of_week, int week_of_month, int day_of_month, int month_of_year) {
        this.separation_count = separation_count;
        this.max_num_of_occurrences = max_num_of_occurrences;
        this.day_of_week = day_of_week;
        this.week_of_month = week_of_month;
        this.day_of_month = day_of_month;
        this.month_of_year = month_of_year;
    }

    public Long getRecurring_type_id() {
        return recurring_type_id;
    }

    public void setRecurring_type_id(Long recurring_type_id) {
        this.recurring_type_id = recurring_type_id;
    }

    public int getSeparation_count() {
        return separation_count;
    }

    public void setSeparation_count(int separation_count) {
        this.separation_count = separation_count;
    }

    public int getMax_num_of_occurrences() {
        return max_num_of_occurrences;
    }

    public void setMax_num_of_occurrences(int max_num_of_occurrences) {
        this.max_num_of_occurrences = max_num_of_occurrences;
    }

    public int getDay_of_week() {
        return day_of_week;
    }

    public void setDay_of_week(int day_of_week) {
        this.day_of_week = day_of_week;
    }

    public int getWeek_of_month() {
        return week_of_month;
    }

    public void setWeek_of_month(int week_of_month) {
        this.week_of_month = week_of_month;
    }

    public int getDay_of_month() {
        return day_of_month;
    }

    public void setDay_of_month(int day_of_month) {
        this.day_of_month = day_of_month;
    }

    public int getMonth_of_year() {
        return month_of_year;
    }

    public void setMonth_of_year(int month_of_year) {
        this.month_of_year = month_of_year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecurringPattern that = (RecurringPattern) o;

        if (getSeparation_count() != that.getSeparation_count()) return false;
        if (getMax_num_of_occurrences() != that.getMax_num_of_occurrences()) return false;
        if (getDay_of_week() != that.getDay_of_week()) return false;
        if (getWeek_of_month() != that.getWeek_of_month()) return false;
        if (getDay_of_month() != that.getDay_of_month()) return false;
        if (getMonth_of_year() != that.getMonth_of_year()) return false;
        return getRecurring_type_id().equals(that.getRecurring_type_id());
    }

    @Override
    public int hashCode() {
        int result = getRecurring_type_id().hashCode();
        result = 31 * result + getSeparation_count();
        result = 31 * result + getMax_num_of_occurrences();
        result = 31 * result + getDay_of_week();
        result = 31 * result + getWeek_of_month();
        result = 31 * result + getDay_of_month();
        result = 31 * result + getMonth_of_year();
        return result;
    }
}
