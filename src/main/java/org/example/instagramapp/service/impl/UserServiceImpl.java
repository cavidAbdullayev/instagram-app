package org.example.instagramapp.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.helper.EmailServiceHelper;
import org.example.instagramapp.helper.TokenServiceHelper;
import org.example.instagramapp.helper.UserServiceHelper;
import org.example.instagramapp.mapper.UserMapper;
import org.example.instagramapp.model.entity.ConfirmationOtp;
import org.example.instagramapp.model.entity.Follower;
import org.example.instagramapp.model.entity.Token;
import org.example.instagramapp.model.entity.Users;
import org.example.instagramapp.model.request.email.EmailRequest;
import org.example.instagramapp.model.request.resetPassword.ResetPasswordRequest;
import org.example.instagramapp.model.request.user.CreateUserRequest;
import org.example.instagramapp.model.response.user.GetUserResponse;
import org.example.instagramapp.repository.FollowerRepository;
import org.example.instagramapp.repository.TokenRepository;
import org.example.instagramapp.repository.UserRepository;
import org.example.instagramapp.service.EmailService;
import org.example.instagramapp.service.OtpService;
import org.example.instagramapp.service.TokenService;
import org.example.instagramapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.example.instagramapp.global.GlobalData.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserServiceImpl implements UserService {
    UserMapper userMapper;
    UserRepository userRepository;
    TokenServiceHelper tokenHelper;
    TokenRepository tokenRepository;
    EmailServiceHelper emailServiceHelper;
    EmailService emailService;
    TokenService tokenService;
    OtpService otpService;
    FollowerRepository followerRepository;
    UserServiceHelper userServiceHelper;

    @Override
    public CreateUserRequest registration(CreateUserRequest request) {

        Users user = Users.builder()
                .username(request.getUsername())
                .id(9)
                .name(request.getName())
                .password(request.getPassword())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .build();
        userServiceHelper.setActiveType(user, request.getActiveTypeId());
        Token token = tokenHelper.getTokenBuild(user);
        EmailRequest emailRequest = emailServiceHelper.sendEmailToVerify(user, token.getToken());
        System.out.println(emailRequest);
        emailService.sendEmail(emailRequest);
        userRepository.save(user);
        tokenRepository.save(token);
        return request;
    }
    @Override
    @Transactional
    public void confirmation(String token) {
        Token confirmationToken = tokenService.getToken(token);
        Users user = confirmationToken.getUser();
        user.setIsEnable(true);
        currentUserId = user.getId();
    }

    @Override
    public void renewPassword(String email) {
        Users user = userServiceHelper.getByEmail(email);
        otpService.createOtpSendToEmail(user);
    }

    @Override
    public void resetPassword(String email, Integer otp, ResetPasswordRequest request) {
        Users user = userServiceHelper.getByEmail(email);
        ConfirmationOtp confirmationOtp = otpService.getOtpService(otp);
        userServiceHelper.checkPasswordForReset(user, confirmationOtp, request);
        user.setPassword(request.getNewPassword());
    }

    @Override
    public GetUserResponse getById(Integer id) {
        return userMapper.mapToResponse(userServiceHelper.getById(id));
    }

    @Override
    public ResponseEntity<String> UpdateActiveType(Integer activeType) {
        userServiceHelper.checkCurrentUser();
        Users user = userRepository.getReferenceById(currentUserId);
        userServiceHelper.setActiveType(user, activeType);
        userRepository.save(user);
        return ResponseEntity.ok(activeTypeUpdated);
    }

    @Override
    public ResponseEntity<String> sendFollow(Integer id) {
        userServiceHelper.checkCurrentUser();
        Users user = userServiceHelper.getById(id);
        Follower follower = userServiceHelper.checkFollower(user);
        Users followingUser = userRepository.getReferenceById(currentUserId);
        follower.setFollowing(followingUser);
        followingUser.setFollowingCount(followingUser.getFollowingCount() + 1);
        user.setFollowerCount(user.getFollowerCount() + 1);
        userRepository.saveAll(Arrays.asList(user, followingUser));
        followerRepository.save(follower);
        return ResponseEntity.ok(followSent);
    }
}