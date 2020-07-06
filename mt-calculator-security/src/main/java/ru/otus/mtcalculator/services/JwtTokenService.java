package ru.otus.mtcalculator.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.otus.mtcalculator.config.JwtConfiguration;
import ru.otus.mtcalculator.config.JwtUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;


@RequiredArgsConstructor
@Service
public class JwtTokenService {


    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private final JwtConfiguration jwtConfiguration;

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = getJwtUser(token);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private JwtUser getJwtUser(String token) {
        DecodedJWT jwt = JWT.decode(token);
        long id = jwt.getClaim("UserId").asLong();
        String userName = jwt.getClaim("UserName").asString();
        return new JwtUser(id, userName, new ArrayList<>());
    }

    public String extractToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfiguration.getSecret());
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withIssuer("auth0")
                    .build(); //Reusable verifier instance
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return false;
    }
}
