package com.advisor.model.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class MessageRequest {

    @NotNull
    private UUID receiverId;

    @NotBlank
    private String msgTime;

    @NotBlank
    private String message;


    public MessageRequest() {
    }

}