package com.backend.event_service.services.jwt;

import com.backend.event_service.dto.AccessToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Value("${jwt.lifetime}")
    private Duration TOKEN_LIFE_TIME;

    public AccessToken getAccessToken(UserDetails details) {
        String accessToken = this.generateAccessToken(details);
        return new AccessToken(accessToken);
    }

    public String getUserLogin(String token) {
        return getClaims(token, SECRET_KEY).getSubject();
    }

    public List<?> getUserRoles(String token) {
        return getClaims(token, SECRET_KEY).get("role", List.class);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        var userName = getClaims(token, SECRET_KEY).getId();
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        var expiration = getClaims(token, SECRET_KEY).getExpiration();
        return expiration.before(new Date());
    }

    private String generateAccessToken(UserDetails details) {
        final Date issuedDate = new Date();
        final Date expiredDate = new Date(issuedDate.getTime() + TOKEN_LIFE_TIME.toMillis());
        List<String> roles = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(details.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .claim("role", roles)
                .compact();
    }
    private Claims getClaims(@NonNull String token, @NonNull String secret) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
