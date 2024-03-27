package com.brokie.discoveryserver;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disabling CSRF protection globally
                .csrf(AbstractHttpConfigurer::disable)
                // Configuring authorization
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                // Ignoring CSRF protection for specific paths
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/eureka/**")
                )
                // Enabling HTTP Basic authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
