package org.example.instagramapp.helper;

import org.example.instagramapp.model.entity.ConfirmationOtp;
import org.example.instagramapp.model.entity.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

import static org.example.instagramapp.global.GlobalData.otpMinute;

@Service
public class OtpServiceHelper {
    public ConfirmationOtp getOtpBuild(Users user) {
        Random random = new Random();
        int otp = random.nextInt(1000, 9999);
        return ConfirmationOtp.builder()
                .user(user)
                .otp(otp)
                .expireDate(LocalDateTime.now().plusMinutes(otpMinute))
                .build();
    }
}