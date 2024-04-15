package com.ds.device_management_microservice.services.impl;

import com.ds.device_management_microservice.dtos.DeviceDataDto;
import com.ds.device_management_microservice.entities.Device;
import com.ds.device_management_microservice.services.DeviceDataProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DeviceDataProducerServiceImpl implements DeviceDataProducerService {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    public DeviceDataProducerServiceImpl(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(Device device, String operation) throws JsonProcessingException {
        DeviceDataDto deviceDataDto = new DeviceDataDto();
        deviceDataDto.setOperation(operation);
        deviceDataDto.setId(device.getId());
        deviceDataDto.setAddress(device.getAddress());
        deviceDataDto.setDescription(device.getDescription());
        deviceDataDto.setMaxEnergyConsumption(device.getMaxEnergyConsumption());
        deviceDataDto.setHolderId(device.getHolderId());
        String message = objectMapper.writeValueAsString(deviceDataDto);
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
