package com.ds.monitoring_microservice.services.impl;

import com.ds.monitoring_microservice.entities.Consumption;
import com.ds.monitoring_microservice.entities.Device;
import com.ds.monitoring_microservice.services.NotificationService;
import com.ds.monitoring_microservice.utils.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    public NotificationServiceImpl(SimpMessagingTemplate simpMessagingTemplate, ObjectMapper objectMapper) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendNotification(Consumption consumption, Device device) throws JsonProcessingException {
        if (consumption.getMeasurementValue() > device.getMaxEnergyConsumption()) {
            String message = computeMessage(device);
            simpMessagingTemplate.convertAndSend("/topic", message);
        }
    }

    private String computeMessage(Device device) throws JsonProcessingException {
        Message message = new Message();
        message.setId(device.getHolderId());
        message.setMessage("The device " + device.getDescription() + " located at " + device.getAddress() + " has exceeded the maximum hourly consumption!");
        return objectMapper.writeValueAsString(message);
    }
}
