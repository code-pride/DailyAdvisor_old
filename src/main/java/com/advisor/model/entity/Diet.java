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
    @Column(name = "diet_text")
    private String dietText;
    @Column(name = "cal")
    private Integer cal;
    @Column(name = "carbohydrates")
    private Float carbohydrates;
    @Column(name = "fat")
    private Integer fat;
    @Column(name = "creat_date")
    private Date createDate;
    @Column(name = "edit_date")
    private Date editDate;


//TODO gettery settery

}
