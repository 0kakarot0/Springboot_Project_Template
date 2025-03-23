package com.example.project_template.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppConfig {

    private final Environment env;
    private final Dotenv dotenv;

    public AppConfig(Environment env) {
        this.env = env;
        this.dotenv = Dotenv.configure()
                .ignoreIfMissing()  // Prevent errors if .env file is missing
                .load();
    }

    // Load from application.properties
    @Value("${app.name}")
    private String appName;

    @Value("${app.environment}")
    private String environment;

    // Load from application.properties OR .env
    @Bean
    public String dbUrl() {
        return dotenv.get("DB_URL", Objects.requireNonNull(env.getProperty("DB_URL")));
    }

    @Bean
    public String jwtSecret() {
        return dotenv.get("JWT_SECRET", Objects.requireNonNull(env.getProperty("JWT_SECRET")));
    }

    @Bean
    public String emailUsername() {
        return dotenv.get("EMAIL_USERNAME", Objects.requireNonNull(env.getProperty("EMAIL_USERNAME")));
    }

    @Bean
    public String emailPassword() {
        return dotenv.get("EMAIL_PASSWORD", Objects.requireNonNull(env.getProperty("EMAIL_PASSWORD")));
    }
}
