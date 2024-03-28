package org.example.instagramapp.repository;

import org.example.instagramapp.model.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> getByToken(String token);
}