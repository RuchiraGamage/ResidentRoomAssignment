package com.company.Security.repository;

import com.company.Security.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByUserId(long userId);

    Optional<PasswordResetToken> findByToken(String token);
}
