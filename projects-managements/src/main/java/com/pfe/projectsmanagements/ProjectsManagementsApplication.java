package com.pfe.projectsmanagements;

import com.pfe.projectsmanagements.services.images.ImageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;
import java.util.Random;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title="Projects Managements",version="2.0",description = ""))
public class ProjectsManagementsApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjectsManagementsApplication.class, args);
	}

	@Resource
	ImageService storageService ;

	@Override
	public void run(String... arg) throws Exception {
		storageService.deleteAll();
		storageService.init();
	}

	@Bean
	public Random getRandom()
	{
		return new Random();
	}

}
