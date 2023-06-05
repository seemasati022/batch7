package com.tejait.batch7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan({"com.tejait.batch7"})
//@EntityScan("com.tejait.batch7")
//@EnableJpaRepositories("com.tejait.batch7")
public class Batch7Application {

	public static void main(String[] args) {
		SpringApplication.run(Batch7Application.class, args);
	}

}
