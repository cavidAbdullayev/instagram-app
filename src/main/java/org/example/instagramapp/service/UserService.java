package org.example.instagramapp.service;

import org.example.instagramapp.model.request.resetPassword.ResetPasswordRequest;
import org.example.instagramapp.model.request.user.CreateUserRequest;
import org.example.instagramapp.model.response.user.GetUserResponse;
import org.springframework.http.ResponseEntity;

public interface UserService {
    CreateUserRequest registration(CreateUserRequest request);

    void confirmation(String token);

    void renewPassword(String email);

    void resetPassword(String email, Integer otp, ResetPasswordRequest request);

    GetUserResponse getById(Integer id);

    ResponseEntity<String> UpdateActiveType(Integer activeType);

    ResponseEntity<String> sendFollow(Integer id);

}