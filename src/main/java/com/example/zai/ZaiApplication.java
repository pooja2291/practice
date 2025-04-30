package com.example.zai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ZaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZaiApplication.class, args);
	}

}
