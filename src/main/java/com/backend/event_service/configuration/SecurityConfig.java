package com.backend.event_service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(c -> c.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of(
                            "http://localhost:5000",
                            "http://localhost:5001",
                            "http://localhost:5002",
                            "http://localhost:5003",
                            "http://localhost:5004",
                            "http://localhost:5005"
                    ));
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/v1/auth/sign-in").permitAll()
                                .requestMatchers("/api/v1/user/new/**").hasAuthority("DEAN")
                                .requestMatchers("/api/v1/company/**").hasAuthority("DEAN")
                                .requestMatchers("/api/v1/event/booking/**").hasAuthority("STUDENT")
                                .requestMatchers("/api/v1/manage/event/**").hasAuthority("MANAGER")
                                .requestMatchers("/api/v1/event/**").authenticated()
                                .anyRequest().authenticated()
                )
                .sessionManagement(c->c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
