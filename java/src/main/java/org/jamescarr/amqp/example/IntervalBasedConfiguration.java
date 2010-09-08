package org.jamescarr.amqp.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

@Configuration
public class IntervalBasedConfiguration extends CommonConfiguration {
	@Bean
	public BeanPostProcessor postProcessor() {
		return new ScheduledAnnotationBeanPostProcessor();
	}
	
	
	@Override
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey("key.b.a");
		template.setExchange(exchange);
		return template;
	}


	@Bean
	public RandomPriceProducer randomPriceProducer(){
		RandomPriceProducer producer = new RandomPriceProducer();
		producer.setRabbitTemplate(rabbitTemplate());
		return producer;
	}
	
}
