package com.ds.chat_microservice.repositories;

import com.ds.chat_microservice.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    Optional<Message> findById(UUID id);
    @Query("SELECT m FROM Message m WHERE (m.from = :from AND m.to = :to) OR (m.to = :from AND m.from = :to)")
    List<Message> findByToAndFromOrFromAndTo(@Param("from") UUID from, @Param("to") UUID to);
}
