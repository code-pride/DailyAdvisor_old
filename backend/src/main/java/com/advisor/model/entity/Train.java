package com.advisor.model.entity;

import com.advisor.model.request.TrainListRequest;
import com.advisor.model.request.TrainingRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "train")
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;

    @Column(name = "status")
    private String status;

    @Column(nullable = false, name = "train_name")
    private String trainName;

    @Column(nullable = false, name = "train_text")
    private String trainText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date", nullable = false, columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private Date createDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "trainings", joinColumns = @JoinColumn(name = "id"))
    private Set<Training> trainings;


    public Train() {
    }

    public Train(User creator, TrainListRequest trainListRequest) {
        this.createdBy = creator;
        Set<TrainingRequest> trainingRequests = trainListRequest.getTrainings();
        Set<Training> trainings = new HashSet<>();
        for (TrainingRequest trainingRequest : trainingRequests) {
            Training training = new Training(trainingRequest);
            trainings.add(training);
        }
        this.trainings = trainings;
        this.status = "published";
        this.trainName = trainListRequest.getTrainName();
        this.trainText = trainListRequest.getTrainText();
    }

}