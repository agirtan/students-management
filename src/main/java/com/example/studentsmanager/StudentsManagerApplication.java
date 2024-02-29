package com.example.studentsmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.studentsmanager.repository")
public class StudentsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentsManagerApplication.class, args);
	}

}
