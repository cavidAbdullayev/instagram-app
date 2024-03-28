package org.example.instagramapp.repository;

import org.example.instagramapp.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);
}