package com.ds.device_management_microservice.dtos;

import com.ds.device_management_microservice.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceSaveDto {
    @NotBlank(message = ValidationMessages.BLANK_DESCRIPTION_VALIDATION_MESSAGE)
    private String description;
    @NotBlank(message = ValidationMessages.BLANK_ADDRESS_VALIDATION_MESSAGE)
    private String address;
    @Positive(message = ValidationMessages.POSITIVE_MAX_ENERGY_CONSUMPTION_VALIDATION_MESSAGE)
    private float maxEnergyConsumption;
    private UUID holderId;
}
