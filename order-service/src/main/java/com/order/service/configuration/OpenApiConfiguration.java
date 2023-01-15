package com.order.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .components(new Components())
        .info(new Info()
            .title("Order Service API")
            .description("Description of the Order Service API")
            .termsOfService("terms")
            .license(new License().name("GNU"))
            .version("1.0")
        );
  }	
	
}
