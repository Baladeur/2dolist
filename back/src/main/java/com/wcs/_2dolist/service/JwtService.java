package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.TokensRequestDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Value("${jwt.accessTokenExpiration}")
    private long accessTokenExpiration;

    @Value("${jwt.refreshTokenExpiration}")
    private long refreshTokenExpiration;

    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public TokensRequestDTO generateTokens(String userEmail) {
        String accessToken = generateAccessToken(userEmail);
        String refreshToken = generateRefreshToken(userEmail);
        return new TokensRequestDTO(accessToken, refreshToken);
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

    public String validateAndGenerateAccessToken(String refreshToken) {
        Claims claims = extractAllClaims(refreshToken);
        //TODO add logic to validate the token
        String userEmail = claims.getSubject();
        return generateAccessToken(userEmail);
    }

    public boolean validateAccessToken(String token, String userEmail) {
        return validateToken(token, userEmail, accessTokenExpiration);
    }

    public boolean validateRefreshToken(String token, String userEmail) {
        return validateToken(token, userEmail, refreshTokenExpiration);
    }

    private boolean validateToken(String token, String userEmail, long expiration) {
        String subject = extractUserEmail(token);
        return subject.equals(userEmail) && isTokenExpired(token) && extractExpiration(token).after(new Date());
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserEmail(token);
        return (username.equals(userDetails.getUsername()) && isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
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

    public Optional<User> validateAndReturnUser(String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();

        Claims body = jwtParser.parseClaimsJws(token).getBody();

        String email = body.getSubject();

        if(Strings.isBlank(email)){
            throw new AuthorizationServiceException("Email not found in the token subject.");
        }

        if(userRepository.existsByEmail(email)){
            throw new AuthorizationServiceException("User not found.");
        }

        return Optional.ofNullable(userRepository.findByEmail(email));

    }

//    public String updateTokenExpiration(String token) {
//        Claims claims = Jwts.parserBuilder()
//                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//
//        claims.setExpiration(new Date(System.currentTimeMillis() + expiration));
//
//        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .signWith(key)
//                .compact();
//    }

}
