package com.ds.device_management_microservice.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceResponseUpdateDto {
    private UUID id;
    private String description;
    private String address;
    private float maxEnergyConsumption;
    private UUID holderId;
}
