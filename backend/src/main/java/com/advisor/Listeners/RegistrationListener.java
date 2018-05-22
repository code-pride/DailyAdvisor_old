package com.advisor.Listeners;

import com.advisor.Events.OnRegistrationCompleteEvent;
import com.advisor.mail.EmailService;
import com.advisor.model.entity.User;
import com.advisor.model.entity.VerificationToken;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements
        ApplicationListener<OnRegistrationCompleteEvent> {


    private final static String VERIFICATION_MAIL_SUBJECT = "Mail verification";

    @Autowired
    private EmailService emailService;

    @Autowired
    private SimpleMailMessage mailMessage;

    @Autowired
    private SimpleMailMessage template;

    @Value("${frontend.server.port}")
    private String serverPort;

    @Autowired
    private VerificationTokenService verificationTokenService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

    @Override
    @Async
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        try {
            verificationTokenService.create(verificationToken);
        } catch (DataRepositoryException e) {
            logger.error("Registration token exists");
        }
        String text = "http://localhost:" + serverPort + "/registrationConfirm/" + token;
        emailService.sendSimpleMessage(user.getEmail(), VERIFICATION_MAIL_SUBJECT, text);
    }

}