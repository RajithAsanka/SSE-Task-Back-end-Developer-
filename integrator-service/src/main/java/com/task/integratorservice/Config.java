package com.task.integratorservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * Developed by P G R Asanka - 901833109V
 */

@Configuration
public class Config {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this is the package name specified in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.task.schemas.corebank");
        return marshaller;
    }

    @Bean
    public CoreBankSOAPConnector soapConnector(Jaxb2Marshaller marshaller) {
        CoreBankSOAPConnector client = new CoreBankSOAPConnector();
        client.setDefaultUri("http://localhost:8082/dummy-core-bank/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
