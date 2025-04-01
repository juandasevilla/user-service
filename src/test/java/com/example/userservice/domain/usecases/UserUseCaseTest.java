package com.example.userservice.domain.usecases;

import com.example.userservice.domain.exceptions.RoleIsRequiredException;
import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.out.RolePersistencePort;
import com.example.userservice.domain.ports.out.UserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

class UserUseCaseTest {

    @Mock
    private UserPersistencePort userPersistencePort;

    @Mock
    private RolePersistencePort rolePersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_roleExists_savesUser() {
        RoleModel role = new RoleModel(1L, "User role", "description");
        UserModel user = new UserModel(1L, "John", "Doe", 123456, "+1234567890", LocalDate.of(2000, 1, 1), "john.doe@example.com", "password", role);

        when(rolePersistencePort.findById(role.getId())).thenReturn(Optional.of(role));

        userUseCase.saveUser(user);

        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void saveUser_roleDoesNotExist_throwsException() {
        RoleModel role = new RoleModel(1L, "User role", "description");
        UserModel user = new UserModel(1L, "John", "Doe", 123456, "+1234567890", LocalDate.of(2000, 1, 1), "john.doe@example.com", "password", role);

        when(rolePersistencePort.findById(role.getId())).thenReturn(Optional.empty());

        assertThrows(RoleIsRequiredException.class, () -> userUseCase.saveUser(user));
        verify(userPersistencePort, never()).saveUser(user);
    }
}