package br.com.mayanna.ses_kafka;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mayanna.ses_kafka.services.SES;

@SpringBootApplication
public class SesKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SesKafkaApplication.class, args);
		
	}

}
