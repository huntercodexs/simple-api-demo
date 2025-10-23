package br.com.bradesco.cnsg.persisteposvenda.application.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguracao {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Persiste Pós-Venda")
                        .version("v1")
                        .description("Documentação da API de persistência de pós-venda."));
    }
}

