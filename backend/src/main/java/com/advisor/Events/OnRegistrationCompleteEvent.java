package com.advisor.Events;

import com.advisor.model.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private User user;

    public OnRegistrationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }
}
