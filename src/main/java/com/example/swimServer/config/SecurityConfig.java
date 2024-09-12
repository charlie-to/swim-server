package com.example.swimServer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.security.authorization.AuthorityAuthorizationManager.hasRole;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

//   /* @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
//    String jwkSetUri;
//
//    @Bean
//    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers(
//                                        "/swagger-ui/**",
//                                        "/swagger-resources/**",
//                                        "/webjars/**",
//                                        "/v3/api-docs/**",
//                                        "/error",
//                                        "/hello"
//                                ).permitAll()
//                                .requestMatchers(
//                                        "/member/swimmer/all",
//                                        "/record"
//                                ).hasRole("ADMIN")
//                                .requestMatchers(
//                                        "/secureHello"
//                                ).authenticated()
//                );
//                .oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwt ->{
//                            Map<String, Collection<String>> realmAccess = jwt.getClaim("realm_access");
//                            Collection<String> roles = realmAccess.get("roles");
//                            var grantedAuthorities = roles.stream()
//                                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                                    .toList();
//                            return new JwtAuthenticationToken(jwt, grantedAuthorities);
//                        }))*//*
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
//                        .jwkSetUri(this.jwkSetUri)))
//                .cors( cors -> cors.configurationSource(corsConfigurationSource()));
//        return http.build();
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
//    }
//
//    private CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addAllowedOrigin("http://localhost:8081");
//        corsConfiguration.addAllowedHeader("*");
//        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
//        corsSource.registerCorsConfiguration("/**", corsConfiguration);
//
//        return corsSource;
//    }*/
}