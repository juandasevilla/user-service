package com.example.userservice.infrastructure.adapters.persistence;
import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.out.UserPersistencePort;
import com.example.userservice.infrastructure.mappers.UserEntityMapper;
import com.example.userservice.infrastructure.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(UserModel userModel) {
        userRepository.save(userEntityMapper.modelToEntity(userModel));
    }
}
