package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
public class MessageRequest {

    @NotNull
    private long receiverId;

    @NotBlank
    private String msgTime;

    @NotBlank
    private String message;


    public MessageRequest() {
    }

}