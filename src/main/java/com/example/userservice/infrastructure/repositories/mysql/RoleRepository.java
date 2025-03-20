package com.example.userservice.infrastructure.repositories.mysql;

import com.example.userservice.infrastructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
    List<RoleEntity> findAll();
}
