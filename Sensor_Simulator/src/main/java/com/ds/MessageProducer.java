package com.ds;

import com.ds.entities.ConsumptionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ds.utils.ConnectionUtils.*;


public class MessageProducer implements Runnable {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final String deviceId;
    private final List<Float> sensorValues;
    private static AtomicInteger index = new AtomicInteger(0);

    public MessageProducer(String deviceId, List<Float> sensorValues) {
        this.deviceId = deviceId;
        this.sensorValues = sensorValues;
    }

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY_NAME);

            ConsumptionData consumptionData = new ConsumptionData(LocalDateTime.now(), deviceId, sensorValues.get(index.intValue()));
            index.getAndIncrement();

            OBJECT_MAPPER.registerModule(new JavaTimeModule());
            String jsonMessage = OBJECT_MAPPER.writeValueAsString(consumptionData);
            System.out.println(jsonMessage);

            channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes());
        } catch (IOException | TimeoutException e) {
            System.out.println(e.getMessage());
        }
    }
}
