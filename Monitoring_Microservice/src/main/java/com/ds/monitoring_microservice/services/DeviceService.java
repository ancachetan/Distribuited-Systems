package com.ds.monitoring_microservice.services;

import com.ds.monitoring_microservice.dtos.DeviceDataDto;
import com.ds.monitoring_microservice.entities.Device;

import java.util.UUID;

public interface DeviceService {
    Device getById(UUID id);

    UUID getHolderIdByDeviceId(UUID id);

    void save(DeviceDataDto deviceDataDto);

    void update(DeviceDataDto deviceDataDto);

    void deleteById(UUID id);
}
