package com.ds.device_management_microservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "description")
    private String description;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "max_energy_consumption", nullable = false)
    private float maxEnergyConsumption;
    @Column(name = "holder_id")
    private UUID holderId;
}
