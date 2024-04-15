package com.ds.monitoring_microservice.controllers.impl;

import com.ds.monitoring_microservice.controllers.ConsumptionController;
import com.ds.monitoring_microservice.security.JwtService;
import com.ds.monitoring_microservice.services.ConsumptionService;
import com.ds.monitoring_microservice.services.DeviceService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ConsumptionControllerImpl implements ConsumptionController {
    private final ConsumptionService consumptionService;
    private final DeviceService deviceService;
    private final JwtService jwtService;

    public ConsumptionControllerImpl(ConsumptionService consumptionService, DeviceService deviceService, JwtService jwtService) {
        this.consumptionService = consumptionService;
        this.deviceService = deviceService;
        this.jwtService = jwtService;
    }

    @Override
    public ResponseEntity<Float> getHourConsumption(String token, UUID deviceId, String date, int hour) {
        UUID holderId = deviceService.getHolderIdByDeviceId(deviceId);
        if (jwtService.authorizeBasedByRoleAndId(token, holderId)) {
            return new ResponseEntity<>(consumptionService.getHourConsumption(date, deviceId, hour), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
}

