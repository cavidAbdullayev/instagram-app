package org.example.instagramapp.repository;

import org.example.instagramapp.model.entity.ConfirmationOtp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<ConfirmationOtp,Integer> {
    @Query(value = "select o from ConfirmationOtp o " +
            "join Users u on o.user=u " +
            "where o.otp=:otp " +
            "and o.expireDate>current_timestamp")
    Optional<ConfirmationOtp> findByOtp(Integer otp);
}