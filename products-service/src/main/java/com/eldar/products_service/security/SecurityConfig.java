package com.eldar.products_service.security;


import com.eldar.products_service.security.components.JwtTokenFilter;
import com.eldar.products_service.security.exceptions.JwtAccessDeniedHandler;
import com.eldar.products_service.security.exceptions.JwtAuthenticationEntryPoint;

import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AntPathRequestMatcher[] publicRoutes = new AntPathRequestMatcher[] {
                new AntPathRequestMatcher("/oauth2/**"),
                new AntPathRequestMatcher("/login/**"),
                new AntPathRequestMatcher("/login-with-oauth/**"),
                new AntPathRequestMatcher("/swagger-ui/**"),
                new AntPathRequestMatcher("/actuator/**")
        };
        AntPathRequestMatcher[] adminRoutes = new AntPathRequestMatcher[]{
                new AntPathRequestMatcher("/products/**", HttpMethod.DELETE),
                new AntPathRequestMatcher("/categories/**", HttpMethod.DELETE),
                new AntPathRequestMatcher("/brands/**", HttpMethod.DELETE)
        };

        AntPathRequestMatcher[] userRoutes = new AntPathRequestMatcher[]{
                new AntPathRequestMatcher("/products/**", HttpMethod.PUT),
                new AntPathRequestMatcher("/products/**", HttpMethod.GET),
                new AntPathRequestMatcher("/products/**", HttpMethod.POST),
                new AntPathRequestMatcher("/categories/**", HttpMethod.PUT),
                new AntPathRequestMatcher("/categories/**", HttpMethod.GET),
                new AntPathRequestMatcher("/categories/**", HttpMethod.POST),
                new AntPathRequestMatcher("/brands/**", HttpMethod.PUT),
                new AntPathRequestMatcher("/brands/**", HttpMethod.GET),
                new AntPathRequestMatcher("/brands/**", HttpMethod.POST)
        };
        jwtTokenFilter.setPublicRoutes(publicRoutes);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll()
                        .requestMatchers(adminRoutes).hasAnyAuthority("ADMIN")
                        .requestMatchers(userRoutes).hasAnyAuthority("ADMIN","USER")
                        .anyRequest().authenticated())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Desactiva sesiones
                .addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("http://localhost:7000", "http://localhost:7001","http://localhost:8083"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    return config;
                }))
                .build();
    }
}
