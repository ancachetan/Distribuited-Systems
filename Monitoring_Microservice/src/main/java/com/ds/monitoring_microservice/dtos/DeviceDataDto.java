package com.ds.monitoring_microservice.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceDataDto {
    private UUID id;
    private String operation;
    private String description;
    private String address;
    private float maxEnergyConsumption;
    private UUID holderId;
}
