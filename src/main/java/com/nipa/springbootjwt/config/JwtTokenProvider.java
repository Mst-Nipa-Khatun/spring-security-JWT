package com.nipa.springbootjwt.config;

import com.nipa.springbootjwt.dto.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
public class JwtTokenProvider {

    private static final String secret_key = "nipa";

    public boolean isValidateToken(String jwtToken, HttpServletRequest request) {
        try {
            Jwts.parser().setSigningKey(secret_key).parseClaimsJws(jwtToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret_key).parseClaimsJws(jwtToken).getBody();
        return Long.valueOf(claims.getSubject());
    }

    public String generateToken(Authentication authentication, HttpServletRequest request) {

        UserPrincipal userPrinciple = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .claim("phone", userPrinciple.getUsername())//payload
                .setSubject(String.valueOf(userPrinciple.getId()))
                .setIssuedAt(now)
                .setExpiration(getExpirationTime(5l))//5 hours validation time.
                .signWith(SignatureAlgorithm.HS512, secret_key)//headers :  signature and secret key
                .compact();
    }
    public static Date getExpirationTime(Long expireHours) {
        Date now = new Date();
        Long expireInMilis = TimeUnit.HOURS.toMillis(expireHours);
        return new Date(expireInMilis + now.getTime());
    }
}
