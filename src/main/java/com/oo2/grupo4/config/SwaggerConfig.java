package com.oo2.grupo4.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Ticketera Soporte")
                .version("1.0")
                .description("Documentación de la API REST para la gestión de tickets y usuarios.")
                .contact(new Contact()
                    .name("Soporte Técnico UNLa")
                    .email("ticketerasoporte@gmail.com")
                    .url("https://github.com/JulyS2001/TP-Oo2-Spring-Grupo4"))
                .license(new License()
                    .name("Licencia MIT")
                    .url("https://opensource.org/licenses/MIT")));
    }
}
