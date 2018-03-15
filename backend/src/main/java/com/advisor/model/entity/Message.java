package com.advisor.model.entity;

import javax.persistence.*;

@Entity
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}