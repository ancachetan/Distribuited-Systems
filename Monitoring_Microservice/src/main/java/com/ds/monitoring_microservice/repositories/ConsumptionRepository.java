package com.ds.monitoring_microservice.repositories;

import com.ds.monitoring_microservice.entities.Consumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConsumptionRepository extends JpaRepository<Consumption, UUID>  {
    Optional<Consumption> findById(UUID id);
    List<Consumption> findAll();
}
