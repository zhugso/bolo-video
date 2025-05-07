package com.zhugso.common.utils;

import com.zhugso.common.exception.BoloException;
import com.zhugso.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("WkDrRGtwpEShcVuNdGaHfpGZ33xxTSMz".getBytes());

    public static String createToken(Long userId, String username) {
        String jwt = Jwts.builder()
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .subject("LOGIN_USER")
                .claim("userId", userId)
                .claim("username", username)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
        return jwt;
    }

    public static void parseToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException e) {
            throw new BoloException(ResultCodeEnum.TOKEN_INVALID);
        }
    }

    public static Long getUserIdToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        return claimsJws.getPayload().get("userId", Long.class);
    }

    public static String getUsernameFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);

        return claimsJws.getPayload().get("username", String.class);
    }

}
