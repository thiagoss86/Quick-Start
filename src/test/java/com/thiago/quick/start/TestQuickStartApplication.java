package com.thiago.quick.start;

import org.springframework.boot.SpringApplication;

public class TestQuickStartApplication {

	public static void main(String[] args) {
		SpringApplication.from(QuickStartApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
