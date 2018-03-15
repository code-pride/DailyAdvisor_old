package com.advisor.model.response;

import com.advisor.model.entity.Message;

public class MessageResponse {

    private long msgId;

    private long senderId;

    private long receiverId;

    private String status;

    private String msgTime;

    private String message;


    public MessageResponse() {
    }

    public MessageResponse(Message message) {
        this.msgId = message.getId();
        this.senderId = message.getSender().getId();
        this.receiverId = message.getReceiver().getId();
        this.status = message.getStatus();
        this.msgTime = message.getMsgTime();
        this.message = message.getMessage();
    }


    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(long receiverId) {
        this.receiverId = receiverId;
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
