package com.ds.device_management_microservice.services.impl;

import com.ds.device_management_microservice.dtos.DeviceResponseUpdateDto;
import com.ds.device_management_microservice.dtos.DeviceSaveDto;
import com.ds.device_management_microservice.entities.Device;
import com.ds.device_management_microservice.exception.DeviceNotFoundException;
import com.ds.device_management_microservice.repositories.DeviceRepository;
import com.ds.device_management_microservice.services.DeviceDataProducerService;
import com.ds.device_management_microservice.services.DeviceService;
import com.ds.device_management_microservice.utils.ErrorMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {
    private static final String INSERT_OPERATION = "INSERT";
    private static final String DELETE_OPERATION = "DELETE";
    private static final String UPDATE_OPERATION = "UPDATE";
    private final DeviceRepository deviceRepository;
    private final ModelMapper modelMapper;
    private final DeviceDataProducerService deviceDataProducerService;

    public DeviceServiceImpl(DeviceRepository deviceRepository, ModelMapper modelMapper, DeviceDataProducerService deviceDataProducerService) {
        this.deviceRepository = deviceRepository;
        this.modelMapper = modelMapper;
        this.deviceDataProducerService = deviceDataProducerService;
    }

    @Override
    public DeviceResponseUpdateDto getById(UUID id) {
        return deviceRepository.findById(id)
                .map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .orElseThrow(() -> new DeviceNotFoundException(ErrorMessages.DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByDescription(String description) {
        return deviceRepository.findAllByDescriptionContaining(description)
                .stream().map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByHolderIdAndDescription(UUID holderId, String description) {
        return deviceRepository.findAllByHolderIdAndDescriptionContaining(holderId, description)
                .stream().map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByAddress(String address) {
        return deviceRepository.findAllByAddressContaining(address)
                .stream().map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByHolderIdAndAddress(UUID holderId, String address) {
        return deviceRepository.findAllByHolderIdAndAddressContaining(holderId, address)
                .stream().map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByHolderId(UUID id) {
        return deviceRepository.findAllByHolderId(id)
                .stream()
                .map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByMaxEnergyConsumption(float maxEnergyConsumption) {
        return deviceRepository.findAllByMaxEnergyConsumption(maxEnergyConsumption)
                .stream()
                .map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAllByHolderIdAndMaxEnergyConsumption(UUID holderId, float maxEnergyConsumption) {
        return deviceRepository.findAllByHolderIdAndMaxEnergyConsumption(holderId, maxEnergyConsumption)
                .stream()
                .map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public List<DeviceResponseUpdateDto> getAll() {
        return deviceRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, DeviceResponseUpdateDto.class))
                .toList();
    }

    @Override
    public DeviceResponseUpdateDto save(DeviceSaveDto deviceSaveDto) throws JsonProcessingException {
        Device device = modelMapper.map(deviceSaveDto, Device.class);
        Device savedDevice = deviceRepository.save(device);
        deviceDataProducerService.sendMessage(savedDevice, INSERT_OPERATION);
        return modelMapper.map(savedDevice, DeviceResponseUpdateDto.class);
    }

    @Override
    public DeviceResponseUpdateDto update(DeviceResponseUpdateDto deviceUpdateDto) throws JsonProcessingException {
        Device foundDevice = deviceRepository.findById(deviceUpdateDto.getId())
                .orElseThrow(() -> new DeviceNotFoundException(ErrorMessages.DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(deviceUpdateDto.getId())));
        if (deviceUpdateDto.getAddress() != null) {
            foundDevice.setAddress(deviceUpdateDto.getAddress());
        }
        if (deviceUpdateDto.getDescription() != null) {
            foundDevice.setDescription(deviceUpdateDto.getDescription());
        }
        if (deviceUpdateDto.getMaxEnergyConsumption() > 0) {
            foundDevice.setMaxEnergyConsumption(deviceUpdateDto.getMaxEnergyConsumption());
        }
        foundDevice.setHolderId(deviceUpdateDto.getHolderId());
        Device updatedDevice = deviceRepository.save(foundDevice);
        deviceDataProducerService.sendMessage(updatedDevice, UPDATE_OPERATION);
        return modelMapper.map(updatedDevice, DeviceResponseUpdateDto.class);
    }

    @Override
    public void deleteById(UUID id) throws JsonProcessingException {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException(ErrorMessages.DEVICE_NOT_FOUND_BY_ID_EXCEPTION_MESSAGE.formatted(id)));
        deviceDataProducerService.sendMessage(device, DELETE_OPERATION);
        deviceRepository.deleteById(id);
    }

    @Override
    public void deleteByHolderId(UUID holderId) {
        deviceRepository.deleteByHolderId(holderId);
    }
}
