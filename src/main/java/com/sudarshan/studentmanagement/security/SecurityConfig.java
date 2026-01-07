package com.sudarshan.studentmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .sessionManagement(session ->
	            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        )
	        .authorizeHttpRequests(auth ->
	            auth
	                // PUBLIC APIs
	                .requestMatchers("/api/auth/**").permitAll()

	                // READ access → USER + ADMIN
	                .requestMatchers(HttpMethod.GET, "/api/students/**")
	                .hasAnyRole("USER", "ADMIN")

	                // WRITE access → ADMIN only
	                .requestMatchers(HttpMethod.POST, "/api/students/**")
	                .hasRole("ADMIN")

	                .requestMatchers(HttpMethod.PUT, "/api/students/**")
	                .hasRole("ADMIN")

	                .requestMatchers(HttpMethod.DELETE, "/api/students/**")
	                .hasRole("ADMIN")

	                // Everything else must be authenticated
	                .anyRequest().authenticated()
	        )
	        .addFilterBefore(
	            jwtAuthFilter,
	            UsernamePasswordAuthenticationFilter.class
	        );

	    return http.build();
	}


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
