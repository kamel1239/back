/*
  2024
*/
package org.project.infrastructure.api.config;

import java.util.List;
import org.project.infrastructure.api.advice.SecurityAdvice;
import org.project.infrastructure.api.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private SecurityAdvice securityAdvice;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // TODO  Disable CSRF
        http.csrf(AbstractHttpConfigurer::disable)
            .cors(c -> c.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(request -> {
                // For products management we need to be authenticated
                request.requestMatchers("/api/products/**");
                // TODO Just to be able to create users without authentication
                request.requestMatchers("/api/auth/**").permitAll();
                request.anyRequest().authenticated();
            }).sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .exceptionHandling(e -> e.accessDeniedHandler(securityAdvice))
            .exceptionHandling(e -> e.authenticationEntryPoint(securityAdvice))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE"));
        configuration.addAllowedHeader("*");
        configuration.addAllowedOriginPattern("/api/products/**");
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/products/**", configuration);
        return source;
    }

}
