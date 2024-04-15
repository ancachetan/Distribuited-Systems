package com.ds.device_management_microservice.services;

import com.ds.device_management_microservice.dtos.DeviceResponseUpdateDto;
import com.ds.device_management_microservice.dtos.DeviceSaveDto;
import com.ds.device_management_microservice.entities.Device;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    DeviceResponseUpdateDto getById(UUID id);

    List<DeviceResponseUpdateDto> getAllByDescription(String description);

    List<DeviceResponseUpdateDto> getAllByHolderIdAndDescription(UUID holderId, String description);

    List<DeviceResponseUpdateDto> getAllByAddress(String address);

    List<DeviceResponseUpdateDto> getAllByHolderIdAndAddress(UUID holderId, String address);

    List<DeviceResponseUpdateDto> getAllByHolderId(UUID id);

    List<DeviceResponseUpdateDto> getAllByMaxEnergyConsumption(float maxEnergyConsumption);

    List<DeviceResponseUpdateDto> getAllByHolderIdAndMaxEnergyConsumption(UUID holderId, float maxEnergyConsumption);

    List<DeviceResponseUpdateDto> getAll();

    DeviceResponseUpdateDto save(DeviceSaveDto deviceSaveDto) throws JsonProcessingException;

    DeviceResponseUpdateDto update(DeviceResponseUpdateDto deviceUpdateDto) throws JsonProcessingException;

    void deleteById(UUID id) throws JsonProcessingException;

    void deleteByHolderId(UUID holderId);
}
