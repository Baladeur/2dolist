package com.wcs._2dolist.service;

import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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

    @Value("${jwt.expiration}")
    private long expiration;

    private final UserRepository userRepository;

    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(String userEmail) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        System.out.println(userEmail);
        return Jwts.builder()
                .subject(userEmail)
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(key)
                .compact();
    }

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return null;
//        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserEmail(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String retrieveToken(HttpServletRequest request){

        String authorizationHeader = request.getHeader("Authorization");

        if(Strings.isBlank(authorizationHeader)){
            throw new AuthorizationServiceException("Token not found !");
        }

        if(!authorizationHeader.startsWith("Bearer")){
            throw new AuthorizationServiceException("Invalid auth !");
        }

        return authorizationHeader.substring(7);

    }

    public Optional<User> validateAndReturnUsername(String token) {
//        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
//
//        Jws<Claims> claimsJws;
//        try {
        //TODO: fix this. parserBuilder is not available in the current version of JJWT
//            claimsJws = Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//        } catch (JwtException e) {
//            throw new AuthorizationServiceException("Invalid token!", e);
//        }
//
//        Claims body = claimsJws.getBody();
//
//        String subject = body.getSubject();
//        if (subject == null || subject.isBlank()) {
//            throw new AuthorizationServiceException("Empty token subject !");
//        }
//
//        if (!userRepository.existsByEmail(subject)) {
//            throw new AuthorizationServiceException("Username not found !");
//        }
//
//        return Optional.ofNullable(userRepository.findFirstByEmail(subject));
        return null;
    }
}
