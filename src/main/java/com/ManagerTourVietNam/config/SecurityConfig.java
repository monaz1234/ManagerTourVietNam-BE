//package com.ManagerTourVietNam.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/home/**", "/about-us", "/customer/**", "/news").permitAll() // được phép truy cập không xác thực
//                        .anyRequest().authenticated() // còn lại phải xác thực chuyển đến trang đăng nhập
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll)
//                .cors((cors) -> cors
//                        .configurationSource(request -> {
//                            var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
//                            corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
//                            corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "HEAD"));
//                            corsConfiguration.setAllowedHeaders(List.of("*"));
//                            corsConfiguration.setAllowCredentials(true);
//                            return corsConfiguration;
//                        })
//                );
//
//        return http.build();
//    }
//}