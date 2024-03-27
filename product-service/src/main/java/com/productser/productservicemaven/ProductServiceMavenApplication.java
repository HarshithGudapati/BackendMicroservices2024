package com.productser.productservicemaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.productser.productservicemaven.repository")


public class ProductServiceMavenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceMavenApplication.class, args);
    }

}
