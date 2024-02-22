package com.dictionary.infrastructure.web.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JwtUtils {

    @Value("${app.jwt.expiration.access-token}")
    private long getExpirationAccessToken;

    @Value("${app.jwt.secret}")
    private String getSecretKey;

    @Value("${app.jwt.issuer}")
    private String getIssuer;

    private final Algorithm getAlgorithm = Algorithm.HMAC256(getSecretKey);

    public String createToken(String subject) {
        var tokenBuilder = JWT.create()
                .withSubject(subject)
                .withIssuer(getIssuer)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + getExpirationAccessToken));

        return tokenBuilder.sign(getAlgorithm);
    }

    public boolean verifyToken(String jwt) {
        try {
            JWT.require(getAlgorithm)
                    .build()
                    .verify(jwt);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public String getUserToken(String token) {
        return JWT.decode(token).getSubject();
    }
}
