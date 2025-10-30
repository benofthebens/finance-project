package org.financeproject.service.expenseservice;

import org.financeproject.service.expenseservice.services.ExpenseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;

import java.util.List;

@SpringBootApplication
@EnableKafka
public class ExpenseServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(ExpenseServiceApplication.class);
	public static void main(String[] args) {
		logger.info("Hello world");
		SpringApplication.run(ExpenseServiceApplication.class, args);
	}
	@Bean
	public ExpenseMapper expenseMapper() {
		return new ExpenseMapper();
	}
}
