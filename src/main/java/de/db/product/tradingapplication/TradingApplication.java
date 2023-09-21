package de.db.product.tradingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Spring Boot Application for the Trading Application.
 *
 * @author Abdallah Emad.
 * @since 19-9-2023
 */
@EnableCaching
@SpringBootApplication
public class TradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradingApplication.class, args);
	}

}
