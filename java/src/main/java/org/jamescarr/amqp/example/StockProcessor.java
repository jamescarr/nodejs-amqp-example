package org.jamescarr.amqp.example;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StockProcessor {
	public static void main(String... args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StockProcessorConfiguration.class);
		SimpleMessageListenerContainer container = (SimpleMessageListenerContainer) context.getBean("listenerContainer");
		StockLookup lookup = (StockLookup) context.getBean("stockLookup");
		container.setMessageListener(lookup);
	}
	
}
