package br.com.mayanna.ses_kafka.controllers;

import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mayanna.ses_kafka.services.ProducerKafka;

@RestController
public class Kafka {
	@GetMapping("/test")
	public void sendMessage() throws InterruptedException, ExecutionException { 
		ProducerKafka.sendMessage("Envio_de_email", "Email de teste!!");
	}
}
