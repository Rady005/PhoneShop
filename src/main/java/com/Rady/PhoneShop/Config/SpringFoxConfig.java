package com.Rady.PhoneShop.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringFoxConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .openapi("3.0.1")  // Add this line to specify OpenAPI version
                .info(new Info()
                        .title("Phone Shop API")
                        .description("API documentation for Phone Shop application")
                        .version("1.0"));
    }
}