package com.daviddev16.security;

import com.daviddev16.usuario.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer";

    private final JwtProviderService jwtService;
    private final UsuarioService usuarioService;


    public JwtAuthenticationFilter(JwtProviderService jwtService,
                                   UsuarioService usuarioService)
    {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorization = httpServletRequest.getHeader("Authorization");

        /* verificar se o token do header da requisição é válido */
        if (authorization != null && authorization.startsWith(BEARER)) {

            final String tokenJwt = authorization.split(" ")[1];

            if (jwtService.verificarSeTokenEhValido(tokenJwt)) {


                final String loginUsuario = jwtService.obterLoginUsuarioDeTokenJwt(tokenJwt);
                final UserDetails userDetails = usuarioService.loadUserByUsername(loginUsuario);

                final UsernamePasswordAuthenticationToken userAuthToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                userAuthToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(userAuthToken);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
