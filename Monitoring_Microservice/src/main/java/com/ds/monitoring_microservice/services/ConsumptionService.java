package com.ds.monitoring_microservice.services;

import com.ds.monitoring_microservice.dtos.ConsumptionDataDto;
import com.ds.monitoring_microservice.entities.Consumption;

import java.util.List;
import java.util.UUID;

public interface ConsumptionService {
    void save(ConsumptionDataDto consumptionDataDto);

    List<Consumption> getAll();

    float getHourConsumption(String date, UUID deviceId, int hour);
}
