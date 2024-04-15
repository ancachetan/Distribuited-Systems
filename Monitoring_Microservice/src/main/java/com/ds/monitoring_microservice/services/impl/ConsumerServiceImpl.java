package com.ds.monitoring_microservice.services.impl;

import com.ds.monitoring_microservice.dtos.ConsumptionDataDto;
import com.ds.monitoring_microservice.dtos.DeviceDataDto;
import com.ds.monitoring_microservice.services.ConsumerService;
import com.ds.monitoring_microservice.services.ConsumptionService;
import com.ds.monitoring_microservice.services.DeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumptionService consumptionService;
    private final DeviceService deviceService;
    private final ObjectMapper objectMapper;

    public ConsumerServiceImpl(ConsumptionService consumptionService, DeviceService deviceService, ObjectMapper objectMapper) {
        this.consumptionService = consumptionService;
        this.deviceService = deviceService;
        this.objectMapper = objectMapper;
    }


    @Override
    @RabbitListener(queues = {"${rabbitmq.device.queue.name}"})
    public void consumeDeviceMessages(String message) throws JsonProcessingException {
        DeviceDataDto deviceDataDto = objectMapper.readValue(message, DeviceDataDto.class);
        System.out.println(message);
        switch (deviceDataDto.getOperation()) {
            case "INSERT" -> deviceService.save(deviceDataDto);
            case "DELETE" -> deviceService.deleteById(deviceDataDto.getId());
            case "UPDATE" -> deviceService.update(deviceDataDto);
        }
    }

    @Override
    @RabbitListener(queues = {"${rabbitmq.consumption.queue.name}"})
    public void consumeConsumptionMessages(String message) throws JsonProcessingException {
        ConsumptionDataDto consumptionDataDto = objectMapper.readValue(message, ConsumptionDataDto.class);
        deviceService.getById(UUID.fromString(consumptionDataDto.getDeviceId()));
        System.out.println(message);
        consumptionService.save(consumptionDataDto);
    }
}
