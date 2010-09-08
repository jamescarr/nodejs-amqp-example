package org.jamescarr.amqp.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

public class RandomPriceProducer {
	private RabbitTemplate rabbitTemplate;
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Scheduled(fixedRate=500)
	public void send(){
		String format = format(Math.random());
		System.out.println("sending " + format);
		rabbitTemplate.convertAndSend(format);
	}

	private String format(double random) {
		NumberFormat formatter = new DecimalFormat("#0.00");
		return formatter.format(random * 100);
	}
}
