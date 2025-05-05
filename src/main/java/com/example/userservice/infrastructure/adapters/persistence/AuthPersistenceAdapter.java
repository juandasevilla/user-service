package com.example.userservice.infrastructure.adapters.persistence;

import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.out.AuthPersistencePort;
import com.example.userservice.infrastructure.entities.UserEntity;
import com.example.userservice.infrastructure.mappers.UserEntityMapper;
import com.example.userservice.infrastructure.repositories.mysql.UserRepository;
import com.example.userservice.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.Jar;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthPersistenceAdapter implements AuthPersistencePort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public UserModel authenticate(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return userEntityMapper.entityToModel(userEntity);
    }

    @Override
    public String generateToken(String email, String role) {
        return jwtProvider.generateToken(email, role);
    }

}
