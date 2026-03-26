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
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) 
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/books/**").permitAll() 
                        .requestMatchers(HttpMethod.POST, "/books/**").hasRole("ADMIN") 
                        .requestMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")  
                        .requestMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN") 
                        .requestMatchers("/h2-console/**").permitAll()
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
