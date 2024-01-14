package com.daviddev16.config.production;

import com.daviddev16.core.profiles.ProductionProfile;
import com.daviddev16.security.JwtAuthenticationFilter;
import com.daviddev16.usuario.UsuarioService;
import com.daviddev16.usuario.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Autenticação em via UsuarioService/Banco de dados para ambiente de produção
 * */
@EnableWebSecurity
@ProductionProfile
public class ProdSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsuarioService usuarioService;
    private final JwtAuthenticationFilter authenticationFilter;

    private final PasswordEncoder passwordEncoder;

    public ProdSecurityConfig(UsuarioService usuarioService,
                              JwtAuthenticationFilter authenticationFilter)
    {
        this.usuarioService = usuarioService;
        this.authenticationFilter = authenticationFilter;

        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /* PARTE DE AUTHENTICAÇÃO */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception
    {
        /*authenticationManagerBuilder
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder);*/
    }

    /* PARTE DE AUTORIZAÇÃO */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf()
                    .disable()

                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
                        .hasAnyRole("USER", "ADMIN")

                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")

                    .antMatchers("/api/pedidos/**")
                        .hasRole("ADMIN")

                    .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                        .permitAll()

                    .antMatchers(HttpMethod.GET, "/api/usuarios/**")
                        .hasRole("ADMIN")

                    .antMatchers("/swagger-resources/**")
                        .permitAll()

                .anyRequest()
                    .authenticated()

                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
