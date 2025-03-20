package com.example.userservice.commons.configuration.beans;

import com.example.userservice.domain.ports.in.RoleServicePort;
import com.example.userservice.domain.ports.out.RolePersistencePort;
import com.example.userservice.domain.usecases.RoleUseCase;
import com.example.userservice.infrastructure.adapters.persistence.RolePersistenceAdapter;
import com.example.userservice.infrastructure.mappers.RoleEntityMapper;
import com.example.userservice.infrastructure.repositories.mysql.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    public RolePersistencePort rolePersistencePort() {
        return new RolePersistenceAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public RoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

}
