package com.advisor.model.response;

import com.advisor.model.entity.Message;
import lombok.Data;

import java.util.UUID;

@Data
public class MessageResponse {

    private UUID msgId;

    private UUID senderId;

    private UUID receiverId;

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

}
