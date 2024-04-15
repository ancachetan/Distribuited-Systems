package com.ds.device_management_microservice.controllers;

import com.ds.device_management_microservice.dtos.DeviceResponseUpdateDto;
import com.ds.device_management_microservice.dtos.DeviceSaveDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/devices")
public interface DeviceController {
    @GetMapping("/getById/{id}")
    ResponseEntity<DeviceResponseUpdateDto> getById(@RequestHeader("Authorization") String token, @PathVariable UUID id) throws Exception;

    @GetMapping("/getAllByDescription/{description}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByDescription(@RequestHeader("Authorization") String token, @PathVariable String description);

    @GetMapping("/getAllByHolderIdAndDescription/{holderId}/{description}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderIdAndDescription(@RequestHeader("Authorization") String token, @PathVariable UUID holderId, @PathVariable String description);

    @GetMapping("/getAllByAddress/{address}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByAddress(@RequestHeader("Authorization") String token, @PathVariable String address);

    @GetMapping("/getAllByHolderIdAndAddress/{holderId}/{address}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderIdAndAddress(@RequestHeader("Authorization") String token, @PathVariable UUID holderId, @PathVariable String address);

    @GetMapping("/getAllByHolderId/{holderId}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderId(@RequestHeader("Authorization") String token, @PathVariable UUID holderId);

    @GetMapping("/getAllByMaxConsumption/{maxEnergyConsumption}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByMaxEnergyConsumption(@RequestHeader("Authorization") String token, @PathVariable float maxEnergyConsumption);

    @GetMapping("/getAllByHolderIdAndMaxConsumption/{holderId}/{maxEnergyConsumption}")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAllByHolderIdAndMaxEnergyConsumption(@RequestHeader("Authorization") String token, @PathVariable UUID holderId, @PathVariable float maxEnergyConsumption);

    @GetMapping("/getAll")
    ResponseEntity<List<DeviceResponseUpdateDto>> getAll(@RequestHeader("Authorization") String token);

    @PostMapping("/save")
    ResponseEntity<DeviceResponseUpdateDto> save(@RequestHeader("Authorization") String token, @Valid @RequestBody DeviceSaveDto deviceSaveDto) throws JsonProcessingException;

    @PutMapping("/update")
    ResponseEntity<DeviceResponseUpdateDto> update(@RequestHeader("Authorization") String token, @Valid @RequestBody DeviceResponseUpdateDto deviceUpdateDto) throws JsonProcessingException;

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Void> deleteById(@RequestHeader("Authorization") String token, @PathVariable UUID id) throws JsonProcessingException;

    @DeleteMapping("/deleteByHolderId/{id}")
    ResponseEntity<Void> deleteByHolderId(@RequestHeader("Authorization") String token, @PathVariable UUID id);
}
