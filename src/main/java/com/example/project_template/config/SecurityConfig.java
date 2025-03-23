package com.example.project_template.config;

import com.example.project_template.security.JwtFilter;
import com.example.project_template.utils.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter filter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    private SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request ->
                                request.requestMatchers(
                                                "/api/v1/user/login",
                                                "/api/v1/user/register",
                                                "/api/v1/admin/login",
                                                "/api/v1/manager/login"
                                        ).permitAll()
                                        .requestMatchers("/api/v1/user/**").hasAuthority(Role.USER.name()).anyRequest().authenticated()
                                        .requestMatchers("/api/v1/admin/**").hasAuthority(Role.ADMIN.name()).anyRequest().authenticated()
                                        .requestMatchers("/api/v1/manager/**").hasAuthority(Role.MANAGER.name()).anyRequest().authenticated()
                )
                .sessionManagement(
                        session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
