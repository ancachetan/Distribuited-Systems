package com.ds.monitoring_microservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ConsumerService {
    void consumeDeviceMessages(String message) throws JsonProcessingException;
    void consumeConsumptionMessages(String message) throws JsonProcessingException;
}
