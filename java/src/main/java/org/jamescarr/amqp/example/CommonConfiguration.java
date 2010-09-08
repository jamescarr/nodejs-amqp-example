package org.jamescarr.amqp.example;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.AbstractRabbitConfiguration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SingleConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

public abstract class CommonConfiguration extends AbstractRabbitConfiguration {

	protected static final String exchange = "some-exchange";
	protected String queueName = "queueC";
	private String routingKey = "key.a.b";

	public CommonConfiguration() {
		super();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		SingleConnectionFactory connectionFactory = new SingleConnectionFactory("localhost");
		connectionFactory.setUsername("guest");
		connectionFactory.setPassword("guest");
		return connectionFactory;
	}

	@Override
	public RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey(routingKey);
		template.setQueue(queueName);
		return template;
	}

	@Bean
	public Binding binding() {
		amqpAdmin().declareQueue(new Queue(queueName));
		Binding binding = new Binding(new Queue(queueName), new DirectExchange(exchange), routingKey);
		amqpAdmin().declareBinding(binding);
		return binding;
	}

}