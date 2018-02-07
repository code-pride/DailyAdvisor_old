package com.advisor.model.entity;

import com.advisor.model.request.DietListRequest;
import com.advisor.model.request.MealRequest;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;

    @OneToMany(cascade = CascadeType.ALL)
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
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

