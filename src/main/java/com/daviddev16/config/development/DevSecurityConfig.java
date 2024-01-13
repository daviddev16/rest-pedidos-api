package com.daviddev16.config.development;

import com.daviddev16.core.profiles.DevelopmentProfile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Autenticação em memória para ambiente de desenvolvimento/teste.
 * */
@DevelopmentProfile
public class DevSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public DevSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /* PARTE DE AUTHENTICAÇÃO */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("daviddev16")
                    .password(passwordEncoder.encode("123456"))
                    .roles("USER");
    }

    /* PARTE DE AUTORIZAÇÃO */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/clientes/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")
                    .antMatchers("/api/pedidos/**")
                        .hasRole("ADMIN")
                .and()
                    .httpBasic();
    }

}
