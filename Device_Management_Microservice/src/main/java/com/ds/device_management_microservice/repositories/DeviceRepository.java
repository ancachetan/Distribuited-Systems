package com.ds.device_management_microservice.repositories;

import com.ds.device_management_microservice.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findById(UUID id);

    List<Device> findAllByDescriptionContaining(String description);

    List<Device> findAllByHolderIdAndDescriptionContaining(UUID id, String description);

    List<Device> findAllByAddressContaining(String address);

    List<Device> findAllByHolderIdAndAddressContaining(UUID id, String address);

    List<Device> findAllByHolderId(UUID id);

    List<Device> findAllByMaxEnergyConsumption(float maxEnergyConsumption);

    List<Device> findAllByHolderIdAndMaxEnergyConsumption(UUID id, float maxEnergyConsumption);

    List<Device> findAll();

    void deleteByHolderId(UUID holderId);
}
