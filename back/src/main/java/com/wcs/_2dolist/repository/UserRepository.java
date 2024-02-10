package com.wcs._2dolist.repository;

import com.wcs._2dolist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByAccessToken(String email);
    User findByEmail(String email);
    Optional<User> findFirstByEmail(String email);
    User findByRegistrationToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.accessToken = :accessToken WHERE u.email = :email")
    void updateAccessToken(String email, String accessToken);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.accessToken = :accessToken, u.refreshToken = :refreshToken WHERE u.email = :email")
    void updateRefreshAndAccessTokens(String email, String refreshToken, String accessToken);

}
