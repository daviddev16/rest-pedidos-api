package com.daviddev16.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

import static com.daviddev16.core.util.DateUtil.*;

@Component
public class JwtProviderService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarTokenJwt(String login) {

        final Date dataExpiracao = criarDateComOperador(LocalDateTime.now(),
                dtTm -> dtTm.plusMinutes(Long.parseLong(expiracao)));

        return Jwts
                .builder()
                    .setSubject(login)
                    .setExpiration(dataExpiracao)
                    .signWith( SignatureAlgorithm.HS512, chaveAssinatura )
                .compact();
    }

    public boolean verificarSeTokenEhValido(String tokenJwt) {
        try {
            Claims claims = obterClaimsDeJwt(tokenJwt);
            LocalDateTime lcDateTime = coverterDateToLocalDateTime(claims.getExpiration());
            return LocalDateTime.now().isBefore(lcDateTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Claims obterClaimsDeJwt(String tokenJwt)
            throws ExpiredJwtException
    {
        return Jwts
                .parser()
                    .setSigningKey(chaveAssinatura)
                    .parseClaimsJws(tokenJwt)
                .getBody();
    }

    public String obterLoginUsuarioDeTokenJwt(String tokenJwt)
            throws ExpiredJwtException
    {
        return obterClaimsDeJwt(tokenJwt).getSubject();
    }

}
