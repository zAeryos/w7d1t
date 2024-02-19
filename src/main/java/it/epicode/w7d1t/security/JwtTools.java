package it.epicode.w7d1t.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import it.epicode.w7d1t.exceptions.UnauthorizedException;
import it.epicode.w7d1t.models.objects.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTools {

    @Value("${spring.jwt.secret}")
    private String  secret;
    @Value("${spring.jwt.expirationMs}")
    private String  expirationMs;

    public String createToken(User user) {

        return Jwts.builder().subject       (user.getUsername())
                             .issuedAt      (new Date(System.currentTimeMillis()))
                             .expiration    (new Date(System.currentTimeMillis() + Long.parseLong(expirationMs)))
                             .signWith      (Keys.hmacShaKeyFor(secret.getBytes()))
                             .compact       ();

    }

    public void validateToken(String token) {
        try {
            Jwts.parser().verifyWith    (Keys.hmacShaKeyFor(secret.getBytes()))
                         .build         ()
                         .parse         (token);
        } catch (Exception e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public String extractUsernameFromToken(String token) {

        return Jwts.parser().verifyWith         (Keys.hmacShaKeyFor(secret.getBytes()))
                            .build              ()
                            .parseSignedClaims  (token)
                            .getPayload         ()
                            .getSubject         ();

    }

}
