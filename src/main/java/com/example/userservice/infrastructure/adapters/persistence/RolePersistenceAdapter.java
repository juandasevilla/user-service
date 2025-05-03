package com.example.userservice.infrastructure.adapters.persistence;

import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.domain.ports.out.RolePersistencePort;

import com.example.userservice.infrastructure.mappers.RoleEntityMapper;
import com.example.userservice.infrastructure.repositories.mysql.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RolePersistencePort {
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(RoleModel roleModel) {
        roleRepository.save(roleEntityMapper.modelToEntity(roleModel));
    }

    @Override
    public RoleModel getRoleByName(String roleName) {
        return roleEntityMapper.entityToModel(roleRepository.findByName(roleName).orElse(null));
    }

    @Override
    public List<RoleModel> getRoles() {
        return roleEntityMapper.entityListToModelList(roleRepository.findAll());
    }

    @Override
    public Optional<RoleModel> findById(Long id) {
        return roleRepository.findById(id).map(roleEntityMapper::entityToModel);
    }
}
