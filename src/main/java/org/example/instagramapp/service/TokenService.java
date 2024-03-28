package org.example.instagramapp.service;

import org.example.instagramapp.model.entity.Token;

public interface TokenService {
    Token getToken(String token);
}