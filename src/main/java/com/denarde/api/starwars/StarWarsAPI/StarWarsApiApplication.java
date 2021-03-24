package com.denarde.api.starwars.StarWarsAPI;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(info = @Info(title = "Star Wars API", version = "1.0", description = "May the force be with you"))
public class StarWarsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsApiApplication.class, args);
	}

}
