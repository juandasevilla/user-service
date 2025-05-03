package com.example.userservice.infrastructure.repositories.mysql;

import com.example.userservice.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
