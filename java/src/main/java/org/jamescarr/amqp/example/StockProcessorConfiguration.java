package org.jamescarr.amqp.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@Configuration
public class StockProcessorConfiguration extends CommonConfiguration{
	@Bean
	public SimpleMessageListenerContainer listenerContainer() {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setQueueName(this.queueName);
		return container;
	}
	@Bean 
	public StockLookup stockLookup(){
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey("key.b.a");
		template.setExchange(exchange);
		
		StockLookup lookup = new StockLookup();
		lookup.setRabbitTemplate(template);
		return lookup;
	}
	
	
}
