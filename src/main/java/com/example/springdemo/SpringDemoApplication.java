package com.example.springdemo;

import com.example.springdemo.model.User;
import com.example.springdemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner setup(UserRepository repository) {
		return (args) -> {
			repository.save(new User("A", 10));
			repository.save(new User("B", 11));
			repository.save(new User("C", 12));
			repository.save(new User("D", 13));
		};
	}

}
