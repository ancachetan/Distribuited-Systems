package com.ds.device_management_microservice.services;

import com.ds.device_management_microservice.entities.Device;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DeviceDataProducerService {
    void sendMessage(Device device, String operation) throws JsonProcessingException;
}
