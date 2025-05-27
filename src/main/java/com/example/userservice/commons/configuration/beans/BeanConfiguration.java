package com.example.userservice.commons.configuration.beans;

import com.example.userservice.domain.ports.in.AuthServicePort;
import com.example.userservice.domain.ports.in.RoleServicePort;
import com.example.userservice.domain.ports.in.UserServicePort;
import com.example.userservice.domain.ports.out.AuthPersistencePort;
import com.example.userservice.domain.ports.out.RolePersistencePort;
import com.example.userservice.domain.ports.out.UserPersistencePort;
import com.example.userservice.domain.usecases.AuthUseCase;
import com.example.userservice.domain.usecases.RoleUseCase;
import com.example.userservice.domain.usecases.UserUseCase;
import com.example.userservice.infrastructure.adapters.persistence.AuthPersistenceAdapter;
import com.example.userservice.infrastructure.adapters.persistence.RolePersistenceAdapter;
import com.example.userservice.infrastructure.adapters.persistence.UserPersistenceAdapter;
import com.example.userservice.infrastructure.mappers.RoleEntityMapper;
import com.example.userservice.infrastructure.mappers.UserEntityMapper;
import com.example.userservice.infrastructure.repositories.mysql.RoleRepository;
import com.example.userservice.infrastructure.repositories.mysql.UserRepository;
import com.example.userservice.infrastructure.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final UserRepository  userRepository;
    private final UserEntityMapper userEntityMapper;
    private final JwtProvider jwtProvider;

    public RolePersistencePort rolePersistencePort() {
        return new RolePersistenceAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public RoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    public UserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), rolePersistencePort());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthPersistencePort authPersistencePort() {
        return new AuthPersistenceAdapter(userRepository, userEntityMapper, passwordEncoder(), jwtProvider);
    }

    @Bean
    public AuthServicePort authServicePort() {
        return new AuthUseCase(authPersistencePort());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200") // Cambia esto a la URL de tu frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}
