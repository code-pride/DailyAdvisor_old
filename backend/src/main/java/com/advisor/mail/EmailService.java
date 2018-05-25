package com.advisor.mail;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);
}
