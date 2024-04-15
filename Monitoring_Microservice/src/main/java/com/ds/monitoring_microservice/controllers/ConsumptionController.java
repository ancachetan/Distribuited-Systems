package com.ds.monitoring_microservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@RequestMapping("/consumptions")
public interface ConsumptionController {
    @GetMapping("/getHourConsumption/{deviceId}/{date}/{hour}")
    ResponseEntity<Float> getHourConsumption(@RequestHeader("Authorization") String token, @PathVariable UUID deviceId, @PathVariable String date, @PathVariable int hour);
}
