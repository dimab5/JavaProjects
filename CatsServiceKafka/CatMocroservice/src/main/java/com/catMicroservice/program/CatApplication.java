package com.catMicroservice.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.catMicroservice")
@EnableJpaRepositories("com.catMicroservice.repositories")
@EntityScan("com.entities")
public class CatApplication {
    public static void main(String [] args) {
        SpringApplication.run(CatApplication.class);
    }
}
