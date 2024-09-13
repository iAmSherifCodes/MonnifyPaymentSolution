package com.wallet.monnify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication @EnableCaching
public class MonnifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonnifyApplication.class, args);
	}

}
