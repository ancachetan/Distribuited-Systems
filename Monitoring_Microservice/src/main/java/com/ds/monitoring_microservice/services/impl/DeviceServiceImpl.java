package com.ds.monitoring_microservice.services.impl;

import com.ds.monitoring_microservice.dtos.DeviceDataDto;
import com.ds.monitoring_microservice.entities.Device;
import com.ds.monitoring_microservice.exceptions.DeviceNotFoundException;
import com.ds.monitoring_microservice.repositories.DeviceRepository;
import com.ds.monitoring_microservice.services.DeviceService;
import com.ds.monitoring_microservice.utils.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.ds.monitoring_microservice.utils.ErrorMessages.DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Device getById(UUID id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(ErrorMessages.DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
    }

    @Override
    public UUID getHolderIdByDeviceId(UUID id) {
        return getById(id).getHolderId();
    }

    @Override
    public void save(DeviceDataDto deviceDataDto) {
        Device device = modelMapper.map(deviceDataDto, Device.class);
        deviceRepository.save(device);
    }

    @Override
    public void update(DeviceDataDto deviceDataDto) {
        Device foundDevice = deviceRepository.findById(deviceDataDto.getId())
                .orElseThrow(() -> new DeviceNotFoundException(ErrorMessages.DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(deviceDataDto.getId())));
        foundDevice.setAddress(deviceDataDto.getAddress());
        foundDevice.setDescription(deviceDataDto.getDescription());
        foundDevice.setHolderId(deviceDataDto.getHolderId());
        foundDevice.setMaxEnergyConsumption(deviceDataDto.getMaxEnergyConsumption());
    }

    @Override
    public void deleteById(UUID id) {
        deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
        deviceRepository.deleteById(id);
    }
}
