package com.advisor.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class MessageRequest {

    @NotNull
    private long receiverId;

    @NotBlank
    private String msgTime;

    @NotBlank
    private String message;


    public MessageRequest() {
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
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