package com.onexshield.wmm.authentication_configuration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webConfigCors implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry coreRegistry) {
        coreRegistry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");
    }
}
