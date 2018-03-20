package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

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