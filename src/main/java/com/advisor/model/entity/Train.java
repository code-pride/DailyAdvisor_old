package com.advisor.model.entity;

import com.advisor.model.request.MealRequest;
import com.advisor.model.request.TrainListRequest;
import com.advisor.model.request.TrainingRequest;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "train_list")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainText() {
        return trainText;
    }

    public void setTrainText(String trainText) {
        this.trainText = trainText;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(Set<Training> trainings) {
        this.trainings = trainings;
    }
}