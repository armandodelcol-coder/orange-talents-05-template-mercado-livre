package br.com.zupacademy.armando.mercadolivre.config.security;

import br.com.zupacademy.armando.mercadolivre.users.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${ml.jwt.expiration}")
    private String expiration;

    @Value("${ml.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        User logged = (User) authentication.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API do Mercado Livre")
                .setSubject(logged.getId().toString())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

}
