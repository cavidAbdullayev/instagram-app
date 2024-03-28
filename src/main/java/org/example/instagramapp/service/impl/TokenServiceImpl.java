package org.example.instagramapp.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.instagramapp.enums.ErrorResponseEnum;
import org.example.instagramapp.model.entity.Token;
import org.example.instagramapp.model.exception.GeneralException;
import org.example.instagramapp.repository.TokenRepository;
import org.example.instagramapp.service.TokenService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TokenServiceImpl implements TokenService {
    TokenRepository tokenRepository;

    @Override
    public Token getToken(String token) {
        return tokenRepository.getByToken(token).orElseThrow(() ->
                new GeneralException(ErrorResponseEnum.TOKEN_NOT_FOUND_ERROR));
    }
}