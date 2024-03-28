package org.example.instagramapp.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public enum ErrorResponseEnum {
    OTP_NOT_FOUND("Otp not found!"),
    FILE_TYPE_ERROR("File type must be video or image!"),
    POST_NOT_FOUND_ID("Post given by id not found!"),
    ALREADY_LIKED_ERROR("You already have liked this post!"),
    POST_NOT_SUITABLE_ERROR("Selected post is not suitable for show!"),
    TOKEN_NOT_FOUND_ERROR("Token not found!"),
    ACTIVE_TYPE_ERROR("Active type must be 1 or 0!"),
    USER_NOT_FOUND_EMAIL("User given by email not found!"),
    USER_NOT_FOUND_OTP("User given by OTP not found!"),
    PASSWORD_NOT_MATCHES("New password and its repeat isn't same!"),
    USER_NOT_FOUND_ID("User given by id not found!"),
    NOT_REGISTERED("You have not registered yet!"),
    INPUT_ERROR("Input error!");
    String message;

    ErrorResponseEnum(String message) {
        this.message = message;
    }
}