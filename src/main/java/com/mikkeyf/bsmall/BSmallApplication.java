package com.mikkeyf.bsmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class BSmallApplication {

	public static void main(String[] args) {
		SpringApplication.run(BSmallApplication.class, args);
	}

}
