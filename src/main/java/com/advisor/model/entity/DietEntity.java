package com.advisor.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "diet", schema = "daily_advisor", catalog = "")
public class DietEntity {
    private int dietId;
    private Integer cal;
    private Double carbohydrates;
    private Timestamp createDate;
    private String dietText;
    private Timestamp editDate;
    private Integer fat;
    private Collection<CalendarEntity> calendarsByDietId;
    private UserEntity userByAuthorId;
    private UserEntity userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "diet_id")
    public int getDietId() {
        return dietId;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }

    @Basic
    @Column(name = "cal")
    public Integer getCal() {
        return cal;
    }

    public void setCal(Integer cal) {
        this.cal = cal;
    }

    @Basic
    @Column(name = "carbohydrates")
    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Basic
    @Column(name = "create_date")
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "diet_text")
    public String getDietText() {
        return dietText;
    }

    public void setDietText(String dietText) {
        this.dietText = dietText;
    }

    @Basic
    @Column(name = "edit_date")
    public Timestamp getEditDate() {
        return editDate;
    }

    public void setEditDate(Timestamp editDate) {
        this.editDate = editDate;
    }

    @Basic
    @Column(name = "fat")
    public Integer getFat() {
        return fat;
    }

    public void setFat(Integer fat) {
        this.fat = fat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DietEntity that = (DietEntity) o;

        if (dietId != that.dietId) return false;
        if (cal != null ? !cal.equals(that.cal) : that.cal != null) return false;
        if (carbohydrates != null ? !carbohydrates.equals(that.carbohydrates) : that.carbohydrates != null)
            return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (dietText != null ? !dietText.equals(that.dietText) : that.dietText != null) return false;
        if (editDate != null ? !editDate.equals(that.editDate) : that.editDate != null) return false;
        if (fat != null ? !fat.equals(that.fat) : that.fat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dietId;
        result = 31 * result + (cal != null ? cal.hashCode() : 0);
        result = 31 * result + (carbohydrates != null ? carbohydrates.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (dietText != null ? dietText.hashCode() : 0);
        result = 31 * result + (editDate != null ? editDate.hashCode() : 0);
        result = 31 * result + (fat != null ? fat.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "dietByDietId")
    public Collection<CalendarEntity> getCalendarsByDietId() {
        return calendarsByDietId;
    }

    public void setCalendarsByDietId(Collection<CalendarEntity> calendarsByDietId) {
        this.calendarsByDietId = calendarsByDietId;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByAuthorId() {
        return userByAuthorId;
    }

    public void setUserByAuthorId(UserEntity userByAuthorId) {
        this.userByAuthorId = userByAuthorId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
