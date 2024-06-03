package com.ownerMicroservice.program;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.ownerMicroservice")
@EnableJpaRepositories("com.ownerMicroservice.repositories")
@EntityScan("com.entities")
public class OwnerApplication {
    public static void main(String [] args) {
        SpringApplication.run(OwnerApplication.class);
    }
}
