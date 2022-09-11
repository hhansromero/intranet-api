package com.intranet.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "com.intranet")
public class IntranetApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntranetApiApplication.class, args);
    }

}
