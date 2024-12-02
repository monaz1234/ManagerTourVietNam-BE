//package com.ManagerTourVietNam.config;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**") // Áp dụng cho tất cả các yêu cầu đến API
//                .allowedOrigins("http://localhost:4200") // Chỉ cho phép yêu cầu từ địa chỉ này
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức HTTP được phép
//                .allowedHeaders("*") // Tất cả các header
//                .allowCredentials(true); // Cho phép gửi thông tin xác thực
//    }
//}
