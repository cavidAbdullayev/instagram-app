package org.example.instagramapp.helper;

import lombok.extern.slf4j.Slf4j;
import org.example.instagramapp.enums.SubjectEnum;
import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.model.request.email.EmailRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
@Service
@Slf4j
public class EmailServiceHelper {
    String http = "http://";
    String host = "localhost";
    String confirmation = "/user/confirmation?token=";
    String resetPassword = "/user/reset-password";
    String username = "?username=";
    String otp = "otp=";

    public EmailRequest sendEmailToVerify(Users user, String token) {
        String url = http + host + getServerPort() + confirmation + token;

        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectEnum.REGISTRATION.name())
                .text("Please, follow the link below to complete your registration:\n" + url)
                .build();

    }

    public EmailRequest sendEmailToResetPassword(Users user, Integer confirmationOtp) {
        String url = http + host + getServerPort() + resetPassword + username +
                user.getEmail() + "&" + otp + confirmationOtp;
        return EmailRequest.builder()
                .to(user.getEmail())
                .subject(SubjectEnum.FORGET_PASSWORD.name())
                .text("Please, follow the below to complete reset your password:\n" + url)
                .build();
    }

    public String getServerPort() {
        return ":" + ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder
                .getRequestAttributes()))
                .getRequest()
                .getServerPort();
    }
}