package com.salepe.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {

    private final JavaMailSender mailSender;

    @Autowired
    public NotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async("mailTaskExecutor")
    public void sendRegistrationMailAsync(String toEmail, String userName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Welcome to Salepe");
            message.setText("Hi " + userName + ", your registration is successful.");
            mailSender.send(message);
            log.info("Registration email sent to {}", toEmail);
        } catch (Exception ex) {
            log.error("Failed to send registration email to {}", toEmail, ex);
        }
    }

    @Async("mailTaskExecutor")
    public void sendLoginNotificationAsync(String toEmail, String userName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Login Alert - Salepe");
            message.setText("Hi " + userName + ", a login to your account was detected at " + java.time.Instant.now() + ". If this wasn't you, please secure your account.");
            mailSender.send(message);
            log.info("Login notification email sent to {}", toEmail);
        } catch (Exception ex) {
            log.error("Failed to send login notification email to {}", toEmail, ex);
        }
    }

}