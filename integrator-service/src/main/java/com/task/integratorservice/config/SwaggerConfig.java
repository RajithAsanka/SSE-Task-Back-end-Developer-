package com.task.integratorservice.config;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Developed by P G R Asanka - 901833109V
 */
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Rest Integrator Service for Dummy Core Bank Soap Services",
                "Access API for Dummy Core Bank",
                "API TOS",
                "Terms of service",
                new Contact("Rajith Asanka", "", "pgrasanka@gmail.com"),
                "License of API", "API license URL", new ArrayList<VendorExtension>());
    }

}
