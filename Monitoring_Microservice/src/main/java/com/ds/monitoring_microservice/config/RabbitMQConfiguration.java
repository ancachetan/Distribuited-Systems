package com.ds.monitoring_microservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Value("${rabbitmq.device.queue.name}")
    private String deviceQueue;
    @Value("${rabbitmq.device.exchange.name}")
    private String deviceExchange;
    @Value("${rabbitmq.device.routing.key}")
    private String deviceRoutingKey;
    @Value("${rabbitmq.consumption.queue.name}")
    private String consumptionQueue;
    @Value("${rabbitmq.consumption.exchange.name}")
    private String consumptionExchange;
    @Value("${rabbitmq.consumption.routing.key}")
    private String consumptionRoutingKey;

    @Bean(name = "deviceQueue")
    public Queue deviceQueue() {
        return new Queue(deviceQueue);
    }

    @Bean(name = "deviceTopic")
    public TopicExchange deviceExchange() {
        return new TopicExchange(deviceExchange);
    }

    @Bean(name = "deviceBinding")
    public Binding deviceBinding(@Qualifier("deviceQueue") Queue queue, @Qualifier("deviceTopic") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue)
                .to(topicExchange)
                .with(deviceRoutingKey);
    }

    @Bean(name = "consumptionQueue")
    public Queue consumptionQueue() {
        return new Queue(consumptionQueue);
    }

    @Bean(name = "consumptionTopic")
    public DirectExchange consumptionExchange() {
        return ExchangeBuilder.directExchange(consumptionExchange)
                .durable(false)
                .build();
    }

    @Bean(name = "consumptionBinding")
    public Binding consumptionBinding(@Qualifier("consumptionQueue") Queue queue, @Qualifier("consumptionTopic") DirectExchange directExchange) {
        return BindingBuilder.bind(queue)
                .to(directExchange)
                .with(consumptionRoutingKey);
    }
}
