package com.example.hitchhikking_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disables CSRF protection for simplicity (not recommended for production)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/users/register", "/login", "/error").permitAll() // Allow public access to registration, login, and error pages
                .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(form -> form
                .loginPage("/login") // Custom login page URL
                .defaultSuccessUrl("/") // Redirect to home page after successful login
                .permitAll() // Allow everyone to access the login page
            )
            .logout(logout -> logout
                .logoutUrl("/logout") // Custom logout URL
                .logoutSuccessUrl("/") // Redirect to home page after successful logout
                .permitAll() // Allow everyone to access the logout page
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Password encryption using BCrypt
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var user = org.springframework.security.core.userdetails.User.withUsername("user")
            .password(passwordEncoder().encode("password")) // Encode password using BCrypt
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
