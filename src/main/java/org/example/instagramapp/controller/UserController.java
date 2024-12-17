package org.example.instagramapp.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.model.request.resetPassword.ResetPasswordRequest;
import org.example.instagramapp.model.request.user.CreateUserRequest;
import org.example.instagramapp.model.response.user.GetUserResponse;
import org.example.instagramapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping("/registration")
    public CreateUserRequest registration(@RequestBody CreateUserRequest request) {
        return userService.registration(request);
    }

    @GetMapping("/confirmation")
    public void confirmation(@RequestParam String token) {
        userService.confirmation(token);
    }

    @PostMapping("/renew-password/{email}")
    public void renewPassword(@PathVariable String email) {
        userService.renewPassword(email);
    }

    @RequestMapping(value = "/reset-password", method = {RequestMethod.GET, RequestMethod.POST})
    public void resetPassword(@RequestParam String email,
                              @RequestParam Integer otp,
                              @RequestBody @Valid ResetPasswordRequest request) {
        userService.resetPassword(email, otp, request);
    }

    @GetMapping("/get-by-id/{id}")
    public GetUserResponse getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/send-follow/{id}")
    public ResponseEntity<String> sendFollow(@PathVariable Integer id) {
        return userService.sendFollow(id);
    }

    @PostMapping("/update-active-type/{type}")
    public ResponseEntity<String> updateActiveType(@PathVariable Integer type) {
        return userService.UpdateActiveType(type);
    }
}