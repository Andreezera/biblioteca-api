package com.unesp.bibliotecaapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("*") // Permitir solicitações de todos os domínios
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
            .allowedHeaders("*") // Permitir todos os cabeçalhos
            .allowCredentials(false) // Permitir envio de credenciais (por exemplo, cookies)
            .maxAge(3600); // Tempo máximo de cache para opções pré-voo (em segundos)

        registry.addMapping("/clientes/alunos/**") // Endpoints para alunos
                .allowedOrigins("*") // Permitir solicitações de todos os domínios
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Permitir todos os cabeçalhos

        registry.addMapping("/clientes/professores/**") // Endpoints para professores
                .allowedOrigins("*") // Permitir solicitações de todos os domínios
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Permitir todos os cabeçalhos
    }
}
