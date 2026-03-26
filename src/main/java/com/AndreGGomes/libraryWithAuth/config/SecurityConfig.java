package com.AndreGGomes.libraryWithAuth.condig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Para o H2 Console
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Recomendado para APIs
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll() // Qualquer um lê
                        .requestMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN") // Apenas Admin cria
                        .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")  // Apenas Admin edita
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN") // Apenas Admin deleta
                        .requestMatchers("/h2-console/**").permitAll() // Libera o banco para você ver
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // Permite login simples via Postman (Auth -> Basic Auth)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}