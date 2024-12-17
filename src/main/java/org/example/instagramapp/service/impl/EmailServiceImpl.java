package org.example.instagramapp.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.model.request.email.EmailRequest;
import org.example.instagramapp.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.example.instagramapp.global.GlobalData.hostEmail;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @SneakyThrows
    @Override
    public void sendEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(hostEmail);
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText());
        mailSender.send(message);
    }
}