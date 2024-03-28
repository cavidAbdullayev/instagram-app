package org.example.instagramapp.service;

import lombok.SneakyThrows;
import org.example.instagramapp.model.request.email.EmailRequest;

public interface EmailService {

    @SneakyThrows
    void sendEmail(EmailRequest request);
}