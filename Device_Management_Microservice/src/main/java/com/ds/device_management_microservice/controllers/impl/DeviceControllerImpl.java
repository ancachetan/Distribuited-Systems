package com.ds.device_management_microservice.controllers.impl;

import com.ds.device_management_microservice.controllers.DeviceController;
import com.ds.device_management_microservice.dtos.DeviceResponseUpdateDto;
import com.ds.device_management_microservice.dtos.DeviceSaveDto;
import com.ds.device_management_microservice.security.JwtService;
import com.ds.device_management_microservice.services.DeviceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DeviceControllerImpl implements DeviceController {
    private final DeviceService deviceService;

    private final JwtService jwtService;

    public DeviceControllerImpl(DeviceService deviceService, JwtService jwtService) {
        this.deviceService = deviceService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<DeviceResponseUpdateDto> getById(String token, UUID id) {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.getById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByDescription(String token, String description) {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.getAllByDescription(description), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderIdAndDescription(String token, UUID holderId, String description) {
        if (jwtService.authorizeBasedByRoleAndId(token, holderId)) {
            return new ResponseEntity<>(deviceService.getAllByHolderIdAndDescription(holderId, description), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByAddress(String token, String address) {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.getAllByAddress(address), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderIdAndAddress(String token, UUID holderId, String address) {
        if (jwtService.authorizeBasedByRoleAndId(token, holderId)) {
            return new ResponseEntity<>(deviceService.getAllByHolderIdAndAddress(holderId, address), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }


    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderId(String token, UUID holderId) {
        if (jwtService.authorizeBasedByRoleAndId(token, holderId)) {
            return new ResponseEntity<>(deviceService.getAllByHolderId(holderId), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByMaxEnergyConsumption(String token, float maxEnergyConsumption) {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.getAllByMaxEnergyConsumption(maxEnergyConsumption), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderIdAndMaxEnergyConsumption(String token, UUID holderId, float maxEnergyConsumption) {
        if (jwtService.authorizeBasedByRoleAndId(token, holderId)) {
            return new ResponseEntity<>(deviceService.getAllByHolderIdAndMaxEnergyConsumption(holderId, maxEnergyConsumption), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<List<DeviceResponseUpdateDto>> getAll(String token) {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.getAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<DeviceResponseUpdateDto> save(String token, DeviceSaveDto deviceSaveDto) throws JsonProcessingException {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.save(deviceSaveDto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<DeviceResponseUpdateDto> update(String token, DeviceResponseUpdateDto deviceUpdateDto) throws JsonProcessingException {
        if (jwtService.authorizeBasedByRole(token)) {
            return new ResponseEntity<>(deviceService.update(deviceUpdateDto), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Void> deleteById(String token, UUID id) throws JsonProcessingException {
        if (jwtService.authorizeBasedByRole(token)) {
            deviceService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<Void> deleteByHolderId(String token, UUID id) {
        if (jwtService.authorizeBasedByRole(token)) {
            deviceService.deleteByHolderId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
