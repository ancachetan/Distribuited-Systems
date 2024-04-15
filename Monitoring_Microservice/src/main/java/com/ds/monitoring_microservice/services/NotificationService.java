package com.ds.monitoring_microservice.services;

import com.ds.monitoring_microservice.entities.Consumption;
import com.ds.monitoring_microservice.entities.Device;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface NotificationService {
    void sendNotification(Consumption consumption, Device device) throws JsonProcessingException;
}
