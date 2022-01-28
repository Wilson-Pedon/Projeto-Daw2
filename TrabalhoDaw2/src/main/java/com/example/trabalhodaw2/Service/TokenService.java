package com.example.trabalhodaw2.Service;

import com.example.trabalhodaw2.Model.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String senha;

    public String gerarToken(Authentication authentication) {

        Cliente logado = (Cliente) authentication.getPrincipal();
        long id = logado.getId();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API")
                .setSubject(Long.toString(id))
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256,senha)
                .compact();
    }

    public boolean TokenValido(String token) {
        try{
            Jwts.parser().setSigningKey(this.senha).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long getIdUsuario(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.senha).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
