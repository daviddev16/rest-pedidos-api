package com.daviddev16.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${api.version}")
    private String versaoAplicacao;

    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                    .apis(RequestHandlerSelectors.withClassAnnotation(RequestMapping.class))
                    .paths(PathSelectors.any())
                .build()
                    .apiInfo(informacoesApi());
    }

    public ApiInfo informacoesApi() {
        return new ApiInfoBuilder()
                .title("API para vendas e pedidos")
                .contact(informacoesContato())
                .description("RESTful API para criação de pedidos")
                .version(versaoAplicacao)
                .build();
    }

    private Contact informacoesContato() {
        return new Contact(
                "David Duarte Pinheiro",
                "https://github.com/daviddev16",
                "davidduartepinheiro@gmail.com");
    }

    public ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        SecurityReference reference = new SecurityReference("JWT", scopes);
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(reference);
        return securityReferences;
    }

}
