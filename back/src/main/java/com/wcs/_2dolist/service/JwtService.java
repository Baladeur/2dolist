package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.AccessTokenResponseDTO;
import com.wcs._2dolist.dto.TokensRequestDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.exception.InvalidAccessTokenException;
import com.wcs._2dolist.exception.InvalidRefreshTokenException;
import com.wcs._2dolist.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.accessTokenLifetimeMillis}")
    private long accessTokenExpiration;

    @Value("${jwt.refreshTokenLifetimeMillis}")
    private long refreshTokenExpiration;

    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TokensRequestDTO generateTokens(String userEmail) {
        String accessToken = generateAccessToken(userEmail);
        String refreshToken = generateRefreshToken(userEmail);
        userRepository.updateRefreshAndAccessTokens(userEmail, refreshToken, accessToken);
        return new TokensRequestDTO(accessToken, refreshToken, userEmail);
    }

    public String generateAccessToken(String userEmail) {
        return generateToken(userEmail, accessTokenExpiration);
    }

    public String generateRefreshToken(String userEmail) {
        return generateToken(userEmail, refreshTokenExpiration);
    }

    private String generateToken(String userEmail, long expiration) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(userEmail)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    private boolean validateToken(String token, String userEmail) throws AuthorizationServiceException {
        String subject = extractUserEmail(token);
        return subject.equals(userEmail) && extractExpiration(token).after(new Date());
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String retrieveToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isBlank(authorizationHeader)) {
            throw new AuthorizationServiceException("Authorization header not found.");
        }

        if (!authorizationHeader.startsWith("Bearer")) {
            throw new AuthorizationServiceException("Bearer not found in the request.");
        }

        return authorizationHeader.substring(7);
    }

    public Optional<User> validateAccessTokenAndReturnUser(String accessToken) throws AuthorizationServiceException {
        String email = extractUserEmail(accessToken);

        System.out.println("Email: " + email);

        if(Strings.isBlank(email)){
            throw new AuthorizationServiceException("Email not found in the token subject.");
        }

        if(!userRepository.existsByEmail(email)){
            throw new AuthorizationServiceException("User not found.");
        }

        if (!userRepository.existsByAccessToken(accessToken)) {
            throw new AuthorizationServiceException("Access token not found in the database.");
        }

        return Optional.ofNullable(userRepository.findByEmail(email));

    }

    public AccessTokenResponseDTO refreshAccessToken(TokensRequestDTO request)
            throws InvalidRefreshTokenException, InvalidAccessTokenException {

        String accessToken = request.getAccessToken();
        String refreshToken = request.getRefreshToken();
        String userEmail = request.getEmail();

        if (!isValidRefreshToken(refreshToken, userEmail)) {
            userRepository.updateRefreshAndAccessTokens(userEmail, null, null);
            throw new InvalidRefreshTokenException("Invalid refresh token");
        }

        if (!isAccessTokenExist(accessToken, userEmail)) {
            throw new InvalidAccessTokenException("Invalid access token");
        }

        String newAccessToken = generateToken(userEmail, accessTokenExpiration);
        userRepository.updateAccessToken(userEmail, newAccessToken);

        return new AccessTokenResponseDTO(newAccessToken);
    }

    private boolean isValidRefreshToken(String token, String userEmail) {
        return validateToken(token, userEmail);
    }

    private boolean isAccessTokenExist(String accessToken, String userEmail) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(userEmail));
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String storedAccessToken = user.getAccessToken();
            // Access token exists
            return accessToken.equals(storedAccessToken);
        }
        return false;
    }

}
