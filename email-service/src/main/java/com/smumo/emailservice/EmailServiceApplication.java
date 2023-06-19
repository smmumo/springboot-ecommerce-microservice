package com.smumo.emailservice;

import com.smumo.emailservice.events.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotifications(OrderPlacedEvent orderPlacedEvent){
		//send email notification
		log.info("Received Notification for Order - {}",orderPlacedEvent.getOrderNumber());

	}

}
