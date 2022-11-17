package com.adnan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods(GET, POST, PUT, DELETE)
//                        .allowedHeaders("*")
//                        .allowedOrigins("*")
//                        .allowCredentials(true);
//            }
//        };
//    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // This wildcard pattern matches any host from domain.com and url patterns like "https:microservice.division.domain.com/version1/some_endpoint"
                registry.addMapping("/**").allowedMethods("*").allowedOriginPatterns("https://*.domain.com");
            }
        };
    }
}
