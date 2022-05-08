package br.com.mayanna.ses_kafka;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mayanna.ses_kafka.services.ConsumerKafka;


@SpringBootApplication
public class SesKafkaApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		SpringApplication.run(SesKafkaApplication.class, args);
		ConsumerKafka.readMessage("test-consumer-group");
	}

}
