package com.example.springtp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
@Profile("!dev")
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(registry -> {
            try {
                registry.requestMatchers("/").permitAll() // anyone can access
                        .requestMatchers("/index").hasAnyRole("TEACHER", "STUDENT") // both roles
                        .requestMatchers("/teacher/**").hasRole("TEACHER") // only teachers
                        .requestMatchers("/student/**").hasRole("STUDENT") // only students
                        .anyRequest().authenticated();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).exceptionHandling(exception -> exception
                        .accessDeniedPage("/access-denied")
            ).oauth2ResourceServer(oauth2Configurer -> oauth2Configurer
            .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt -> {
                Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
                Collection<String> roles = realmAccess.get("roles");

                // filter only your custom roles (ignore system roles)
                List<SimpleGrantedAuthority> grantedAuthorities = roles.stream()
                        .filter(role -> role.equals("TEACHER") || role.equals("STUDENT"))
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .toList();

                grantedAuthorities.forEach(e -> System.err.println(e.getAuthority()));
                return new JwtAuthenticationToken(jwt, grantedAuthorities);
            }))
        );
        return httpSecurity.build();
    }
}
