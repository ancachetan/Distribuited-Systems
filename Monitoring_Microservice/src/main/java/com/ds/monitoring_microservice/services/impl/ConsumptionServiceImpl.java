package com.ds.monitoring_microservice.services.impl;

import com.ds.monitoring_microservice.dtos.ConsumptionDataDto;
import com.ds.monitoring_microservice.entities.Consumption;
import com.ds.monitoring_microservice.entities.Device;
import com.ds.monitoring_microservice.repositories.ConsumptionRepository;
import com.ds.monitoring_microservice.services.ConsumptionService;
import com.ds.monitoring_microservice.services.DeviceService;
import com.ds.monitoring_microservice.services.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ConsumptionServiceImpl implements ConsumptionService {
    private final DeviceService deviceService;
    private final ConsumptionRepository consumptionRepository;
    private final ModelMapper modelMapper;
    private final NotificationService notificationService;

    public ConsumptionServiceImpl(DeviceService deviceService, ConsumptionRepository consumptionRepository, ModelMapper modelMapper, NotificationService notificationService) {
        this.deviceService = deviceService;
        this.consumptionRepository = consumptionRepository;
        this.modelMapper = modelMapper;
        this.notificationService = notificationService;
    }

    @Override
    public void save(ConsumptionDataDto consumptionDataDto) {
        Consumption consumption = modelMapper.map(consumptionDataDto, Consumption.class);
        Device device = deviceService.getById(UUID.fromString(consumptionDataDto.getDeviceId()));
        Optional<Consumption> foundConsumption = getByDeviceAndDateAndHour(consumption.getDate(), consumption.getDevice().getId());
        foundConsumption.ifPresentOrElse(
                (consumptionFound) -> {
                    consumptionFound.setDate(consumption.getDate());
                    consumptionFound.setMeasurementValue(consumptionFound.getMeasurementValue() + consumption.getMeasurementValue());
                    consumptionFound.setDevice(device);
                    Consumption savedConsumption = consumptionRepository.save(consumptionFound);
                    try {
                        notificationService.sendNotification(savedConsumption, device);
                    } catch (JsonProcessingException e) {
                        System.out.println(e.getMessage());
                    }
                },
                () -> {
                    consumption.setDevice(device);
                    Consumption savedConsumption = consumptionRepository.save(consumption);
                    try {
                        notificationService.sendNotification(savedConsumption, device);
                    } catch (JsonProcessingException e) {
                        System.out.println(e.getMessage());
                    }
                }
        );
    }

    @Override
    public List<Consumption> getAll() {
        return consumptionRepository.findAll();
    }

    @Override
    public float getHourConsumption(String date, UUID deviceId, int hour) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateTransformed = LocalDate.parse(date, formatter);
        Optional<Consumption> foundConsumption = getByDeviceAndDateAndHour(dateTransformed.atTime(hour, 0), deviceId);
        return foundConsumption.map(Consumption::getMeasurementValue).orElse(0F);
    }

    private Optional<Consumption> getByDeviceAndDateAndHour(LocalDateTime dateTime, UUID deviceId) {
        List<Consumption> consumptions = getAll();
        for (Consumption consumption : consumptions) {
            if (consumption.getDate().toString().substring(0, 10).equals(dateTime.toString().substring(0, 10))
                    && consumption.getDate().getHour() == dateTime.getHour() && consumption.getDevice().getId().equals(deviceId)) {
                return Optional.of(consumption);
            }
        }
        return Optional.empty();
    }
}
