package com.advisor.model.entity;

import com.advisor.model.request.DietListRequest;
import com.advisor.model.request.MealRequest;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Data
@Table(name = "diet")
public class Diet {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "diet_meal", joinColumns = @JoinColumn(name = "id"))
    private Set<Meal> meals;

    @Column(name = "status")
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;


    public Diet() {
    }

    public Diet(User creator, DietListRequest dietListRequest) {
        this.createdBy = creator;
        Set<MealRequest> mealsRequests = dietListRequest.getMeals();
        Set<Meal> meals = new HashSet<>();
        for (MealRequest mealRequest : mealsRequests) {
            Meal meal = new Meal(mealRequest);
            meals.add(meal);
        }
        this.meals = meals;
        this.status = "published";
        this.createDate = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Diet diet = (Diet) o;
        return Objects.equals(getCreatedBy(), diet.getCreatedBy()) &&
                Objects.equals(getMeals(), diet.getMeals()) &&
                Objects.equals(getStatus(), diet.getStatus()) &&
                Objects.equals(getCreateDate(), diet.getCreateDate());
    }
}

