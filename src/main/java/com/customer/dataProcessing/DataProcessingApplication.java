package com.customer.dataProcessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class DataProcessingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataProcessingApplication.class, args);
	}
//	@Bean
//	public Executor asyncExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(10);
//		executor.setMaxPoolSize(10);
//		executor.setQueueCapacity(500);
//		executor.setThreadNamePrefix("JDAsync-");
//		executor.initialize();
//		return executor;
//	}

}
