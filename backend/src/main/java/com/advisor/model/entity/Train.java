package com.advisor.model.entity;

import com.advisor.model.request.TrainListRequest;
import com.advisor.model.request.TrainingRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.*;


@Entity
@Data
@Table(name = "train")
public class Train {
    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private User createdBy;

    @Column(name = "status")
    private String status;

    @Column(nullable = false, name = "train_name")
    private String trainName;

    @Column(nullable = false, name = "train_text")
    private String trainText;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Train train = (Train) o;
        return Objects.equals(getCreatedBy(), train.getCreatedBy()) &&
                Objects.equals(getStatus(), train.getStatus()) &&
                Objects.equals(getTrainName(), train.getTrainName()) &&
                Objects.equals(getTrainText(), train.getTrainText()) &&
                Objects.equals(getCreateDate(), train.getCreateDate()) &&
                Objects.equals(getTrainings(), train.getTrainings());
    }
}