package program;

import abstractions.repositories.ICatRepository;
import abstractions.repositories.IOwnerRepository;
import app.cats.CatService;
import contracts.cats.ICatService;
import dataAccess.migrations.Init;
import dataAccess.repositories.OwnerRepository;
import models.cats.Cat;
import models.cats.CatColor;
import models.cats.CatDto;
import models.owners.Owner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"catHandlers", "ownerHandlers", "abstractions", "app", "dataAccess"})
public class Application {
    public static void main(String[] args) {
        Init.migrate();
        SpringApplication.run(Application.class, args);
//
//        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
//        ICatService catService = context.getBean(ICatService.class);
//        ICatRepository catRepository = context.getBean(ICatRepository.class);
//
//        List<Cat> catDtoList = catRepository.findCatsByColor(CatColor.BLACK);
//
//        for (Cat catDto : catDtoList) {
//            System.out.println(catDto.getName());
//        }
//
//        System.out.println(1);
    }
}