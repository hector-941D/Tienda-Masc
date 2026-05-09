package com.tienda_masc.tienda_masc.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // <--- ESTO ES VITAL para que Postman funcione
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/**").permitAll() // Permite todo lo que empiece con /api/v1/
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}
