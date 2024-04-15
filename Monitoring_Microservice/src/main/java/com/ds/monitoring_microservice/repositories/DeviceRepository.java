package com.ds.monitoring_microservice.repositories;

import com.ds.monitoring_microservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findById(UUID id);
    List<Device> findAll();
}
