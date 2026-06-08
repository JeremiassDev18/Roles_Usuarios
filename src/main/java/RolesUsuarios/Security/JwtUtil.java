package RolesUsuarios.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Convierte el String secret en una SecretKey que JJWT puede usar
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // GENERA el token con la cédula del usuario
    public String generarToken(String cedula, String rol) {
        return Jwts.builder()
                .subject(cedula)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    // EXTRAE todos los claims (datos) del token
    private Claims extraerClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // EXTRAE la cédula del token (el subject)
    public String extraerCedula(String token) {
        return extraerClaims(token).getSubject();
    }

    // EXTRAE el rol del token
    public String extraerRol(String token) {
        return extraerClaims(token).get("rol", String.class);
    }

    // VALIDA que el token no esté expirado y sea del usuario correcto
    public boolean esValido(String token, String cedula) {
        String cedulaDelToken = extraerCedula(token);
        return cedulaDelToken.equals(cedula) && !estaExpirado(token);
    }

    // Verifica si el token ya expiró
    private boolean estaExpirado(String token) {
        return extraerClaims(token).getExpiration().before(new Date());
    }
}