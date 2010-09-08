package org.jamescarr.amqp.example;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import com.google.common.collect.ImmutableMap;

public class StockLookup implements MessageListener{
	private static Map<String, Double> STOCKS = ImmutableMap.of("GOOG", 120.22, "IBM", 32.11);
	private RabbitTemplate rabbitTemplate;
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public void onMessage(Message msg) {
		final Double stockPrice = STOCKS.get(new String(msg.getBody()));
		rabbitTemplate.convertAndSend(stockPrice+"");
	}

	
}
