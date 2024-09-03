package com.example.hitchhikking_app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        // Serve static resources from /static directory
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        
        // Serve Thymeleaf templates from /templates directory
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");
        
        // Serve any other resources from a custom path if needed
        // Example: Serve resources from /resources directory
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");
    }
}
