package org.example.instagramapp.service;

import org.example.instagramapp.model.entity.ConfirmationOtp;
import org.example.instagramapp.model.entity.Users;

public interface OtpService {
    ConfirmationOtp getOtpService(Integer otp);

    void createOtpSendToEmail(Users user);
}