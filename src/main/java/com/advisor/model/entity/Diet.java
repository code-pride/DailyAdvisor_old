package com.advisor.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diet")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "diet_id")
    private int dietId;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
//    private User userId;
    @Column(nullable = false, name = "diet_text")
    private String dietText;
    @Column(name = "cal")
    private Integer cal;
    @Column(name = "carbohydrates")
    private Float carbohydrates;
    @Column(name = "fat")
    private Integer fat;
    @Column(nullable = false, name = "create_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(nullable = false, name = "edit_date", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;


//TODO gettery settery

}
