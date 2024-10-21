package com.abraham.creditservice;

import com.abraham.creditservice.repository.CreditRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing
public class CreditServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CreditServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(CreditRepository creditRepository) {
		return args -> {
			creditRepository.createProcedure();
		};
	}
}
