package com.unesp.bibliotecaapi.config;

import com.unesp.bibliotecaapi.BibliotecaApiApplication;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaApiApplication.class, args);
    }

    @Bean
    public GroupedOpenApi bibliotecaApi() {
        return GroupedOpenApi.builder()
                .group("biblioteca-api")
                .packagesToScan("com.unesp.bibliotecaapi.controller")
                .build();
    }
}
