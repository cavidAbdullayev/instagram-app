package org.example.instagramapp.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.enums.ErrorResponseEnum;
import org.example.instagramapp.helper.EmailServiceHelper;
import org.example.instagramapp.helper.OtpServiceHelper;
import org.example.instagramapp.model.entity.ConfirmationOtp;
import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.model.exception.GeneralException;
import org.example.instagramapp.model.request.email.EmailRequest;
import org.example.instagramapp.repository.OtpRepository;
import org.example.instagramapp.service.EmailService;
import org.example.instagramapp.service.OtpService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OtpServiceImpl implements OtpService {
    OtpRepository otpRepository;
    EmailServiceHelper emailServiceHelper;
    EmailService emailService;
    OtpServiceHelper otpServiceHelper;
    @Override
    public ConfirmationOtp getOtpService(Integer otp) {
        return otpRepository.findByOtp(otp).orElseThrow(() ->
                new GeneralException(ErrorResponseEnum.OTP_NOT_FOUND));
    }
    @Override
    public void createOtpSendToEmail(Users user) {
        ConfirmationOtp confirmationOtp = otpServiceHelper.getOtpBuild(user);
        otpRepository.save(confirmationOtp);
        EmailRequest emailRequest = emailServiceHelper.sendEmailToResetPassword(user, confirmationOtp.getOtp());
        emailService.sendEmail(emailRequest);
    }
}