package com.example.project_template.config;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConfig {
    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${JWT_SECRET}")
    private String jwtSecret;

}
