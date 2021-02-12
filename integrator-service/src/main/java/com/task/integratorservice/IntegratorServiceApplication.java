package com.task.integratorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Developed by P G R Asanka - 901833109V
 */

@SpringBootApplication
@EnableSwagger2
public class IntegratorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntegratorServiceApplication.class, args);
    }

}
