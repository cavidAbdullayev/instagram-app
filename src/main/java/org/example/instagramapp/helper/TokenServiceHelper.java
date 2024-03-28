package org.example.instagramapp.helper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.model.entity.Token;
import org.example.instagramapp.model.entity.Users;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TokenServiceHelper {
    public Token getTokenBuild(Users user) {
        return Token.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .build();
    }
}