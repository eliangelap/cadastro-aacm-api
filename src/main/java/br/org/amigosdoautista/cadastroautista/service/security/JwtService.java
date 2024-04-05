package br.org.amigosdoautista.cadastroautista.service.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.org.amigosdoautista.cadastroautista.model.dto.security.JwtAuthenticationResponse;

@Service
public class JwtService {

    private static final Long EXPIRATION_MILLIS = (1000 * 60 * 60L);

    @Value("${token.signing.key}")
    private String jwtSigningKey;

    @Value("${token.issuer}")
    private String jwtIssuer;

    public String extractUserName(String token) {
        return extractClaim(token, DecodedJWT::getSubject);
    }

    public JwtAuthenticationResponse generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<DecodedJWT, T> claimsResolvers) {
        final DecodedJWT claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private JwtAuthenticationResponse generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_MILLIS);
        Algorithm algorithm = Algorithm.HMAC256(jwtSigningKey);
        String token = JWT.create().withClaim("extras", extraClaims).withIssuer(jwtIssuer)
                .withSubject(userDetails.getUsername()).withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expiration).sign(algorithm);

        return JwtAuthenticationResponse.builder().token(token).username(userDetails.getUsername())
                .expiration(expiration).build();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, DecodedJWT::getExpiresAt);
    }

    private DecodedJWT extractAllClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSigningKey);
        return JWT.require(algorithm).withIssuer(jwtIssuer).build().verify(token);
    }

}
