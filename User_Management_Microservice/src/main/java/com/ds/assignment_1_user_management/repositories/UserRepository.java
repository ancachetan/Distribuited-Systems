package com.ds.assignment_1_user_management.repositories;

import com.ds.assignment_1_user_management.entities.Role;
import com.ds.assignment_1_user_management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);

    List<User> findAllByNameContaining(String name);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    List<User> findAllByRole(Role role);
}
