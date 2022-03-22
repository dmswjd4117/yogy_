package com.example.demo.security;

import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Arrays;
import java.util.Date;

public class Jwt {

    private final String issuer;
    private final String clientSecret;
    private final int expirySeconds;
    private final JWTVerifier jwtVerifier;
    private final Algorithm algorithm;

    public Jwt(String issuer, String clientSecret, int expirySeconds) {
        this.issuer = issuer;
        this.clientSecret = clientSecret;
        this.expirySeconds = expirySeconds;
        this.algorithm = Algorithm.HMAC512(clientSecret);
        this.jwtVerifier = com.auth0.jwt.JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }


    public Claims verify(String token) throws JWTVerificationException {
        return new Claims(jwtVerifier.verify(token));
    }

    public String newToken(Claims claims) {
        Date now = new Date();
        JWTCreator.Builder builder = com.auth0.jwt.JWT.create();
        builder.withIssuer(issuer);
        builder.withIssuedAt(now);
        if (expirySeconds > 0) {
            builder.withExpiresAt(new Date(now.getTime() + expirySeconds * 1_000L));
        }
        builder.withClaim("id", claims.id);
        builder.withClaim("email", claims.email);
        builder.withArrayClaim("roles", claims.roles);

        return builder.sign(algorithm);
    }

    static public class Claims {
        Long id;
        String email;
        String[] roles;
        Date iat;
        Date exp;

        public Claims(){}

        public Claims(DecodedJWT decodedJWT){
            Claim userKey = decodedJWT.getClaim("id");
            if (!userKey.isNull())
                this.id = userKey.asLong();
            Claim email = decodedJWT.getClaim("email");
            if (!email.isNull())
                this.email = email.asString();
            Claim roles = decodedJWT.getClaim("roles");
            if (!roles.isNull())
                this.roles = roles.asArray(String.class);
            this.iat = decodedJWT.getIssuedAt();
            this.exp = decodedJWT.getExpiresAt();
        }

        public static Claims of(Long userKey, String email, String[] roles){
            Claims claims = new Claims();
            claims.id = userKey;
            claims.email = email;
            claims.roles = roles;
            return claims;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("id", id)
                    .append("email", email)
                    .append("roles", Arrays.toString(roles))
                    .append("iat", iat)
                    .append("exp", exp)
                    .toString();
        }

    }

}
