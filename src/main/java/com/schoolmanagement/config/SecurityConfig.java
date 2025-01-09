package com.schoolmanagement.config;

import com.schoolmanagement.util.JwtAuthenticationFilter;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                // Disable CSRF (considering you're building an API)
//                .csrf(csrf -> csrf.disable())
//
//                // Configure HTTP Security rules
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/login").permitAll()  // Allow login endpoint
//                        .requestMatchers("/api/students").hasRole("USER")
//                        .requestMatchers("/swagger-ui/**").permitAll() // Allow access to Swagger UI
//                        .requestMatchers("/v3/api-docs/**").permitAll()// Protect this endpoint with the USER role
//                        .anyRequest().authenticated()  // Secure all other endpoints
//                )
//
//                // Add the JWT authentication filter before the UsernamePasswordAuthenticationFilter
//                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/login").permitAll() // Allow access to login endpoint
                        .requestMatchers("/api/auth/login").permitAll()
                        //.requestMatchers("/api/admin").hasRole("ADMIN") // Access only for ROLE_ADMIN
                        .requestMatchers("/api/students/addStudent").permitAll()
                        .requestMatchers("/api/students/{id}").permitAll()
                        //.requestMatchers("/api/students").hasRole("USER")
                        .requestMatchers("/api/students/class/{className}/{section}").permitAll()
                       // .requestMatchers("/api/students/addStudent").hasRole("USER")
                        .requestMatchers("/swagger-ui/**").permitAll() // Allow access to Swagger UI
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("api/classes/allclasses").permitAll()
                        .requestMatchers("/api/teachers").permitAll()
                        .requestMatchers("/api/teachers/{id}").permitAll()
                        .requestMatchers("/api/teachers/addTeacher").permitAll()
                        .requestMatchers("/api/teachers/updateTeacher/{id}").permitAll()
                        .requestMatchers("class/{className}").permitAll()
                        .requestMatchers("/api/teachers/updateTeacher/**").permitAll()
                        .requestMatchers("/api/students").permitAll()// Allow access to OpenAPI documentation
                        .requestMatchers("/api/students/deleteStudent/{id}").permitAll()
                        .requestMatchers("api/students/updateStudent/{id}").permitAll()
                        .requestMatchers("/api/students/addStudent)").permitAll()
                        .anyRequest().authenticated()); // All other requests require authentication

        return http.build();
    }

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info().title("API Documentation").version("1.0"))
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new io.swagger.v3.oas.models.Components()
//                        .addSecuritySchemes("bearerAuth",
//                                new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")));
//    }
}