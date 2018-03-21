package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "message")
public class Message {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    private User sender;

    @OneToOne(cascade = CascadeType.ALL)
    private User receiver;

    @Column(name = "status")
    private String status;

    @Column(name = "msg_time")
    private String msgTime;

    @Column(name = "message")
    private String message;

    public Message() {
    }

    public Message(User sender, User receiver, String msgTime, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = "sent";
        this.msgTime = msgTime;
        this.message = message;
    }

}