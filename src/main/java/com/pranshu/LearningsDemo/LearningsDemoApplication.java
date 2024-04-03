package com.pranshu.LearningsDemo;

import com.pranshu.LearningsDemo.service.InitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LearningsDemoApplication implements CommandLineRunner {

	private InitService initService;

	public LearningsDemoApplication(InitService initService) {
		this.initService = initService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LearningsDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initService.initialise();
	}
}
