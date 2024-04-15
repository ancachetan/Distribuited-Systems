package com.ds.monitoring_microservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Device implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "description")
    private String description;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "max_energy_consumption", nullable = false)
    private float maxEnergyConsumption;
    @Column(name = "holder_id")
    private UUID holderId;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "device")
    @ToString.Exclude
    private List<Consumption> consumptions;
}
