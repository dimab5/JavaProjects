package program;

import dataAccess.migrations.Init;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"catControllers", "ownerControllers", "abstractions", "app", "dataAccess",
"config"})
public class Application {
    public static void main(String[] args) {
        Init.migrate();
        SpringApplication.run(Application.class, args);
    }
}