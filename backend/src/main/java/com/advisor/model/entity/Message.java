package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(getSender(), message1.getSender()) &&
                Objects.equals(getReceiver(), message1.getReceiver()) &&
                Objects.equals(getStatus(), message1.getStatus()) &&
                Objects.equals(getMsgTime(), message1.getMsgTime()) &&
                Objects.equals(getMessage(), message1.getMessage());
    }
}