package com.isaac.todoapi.configurations;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ToDo API")
                        .version("1.0.0")
                        .description("API documentation"))
                .externalDocs(new ExternalDocumentation()
                        .description("Learn more")
                                .description("Learn more")
                                .url("https://todo-api-documentation.com"));
    }
}