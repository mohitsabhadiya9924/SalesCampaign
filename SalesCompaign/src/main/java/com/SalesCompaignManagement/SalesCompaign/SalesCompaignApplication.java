package com.SalesCompaignManagement.SalesCompaign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SalesCompaignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesCompaignApplication.class, args);
		System.out.println("API is Starting...");
	}
}
