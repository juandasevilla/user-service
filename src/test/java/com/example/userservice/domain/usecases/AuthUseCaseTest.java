package com.example.userservice.domain.usecases;

import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.out.AuthPersistencePort;
import com.example.userservice.domain.utils.LoginDomainResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthUseCaseTest {

    @Mock
    private AuthPersistencePort authPersistencePort;

    @InjectMocks
    private AuthUseCase authUseCase;

    private final String email = "test@example.com";
    private final String password = "password123";
    private final Long userId = 1L;
    private final String roleName = "ADMIN";
    private final String token = "jwt-token-example";

    private UserModel userModel;

    @BeforeEach
    void setUp() {
        RoleModel roleModel = new RoleModel(1L, roleName, "Description of role");
        roleModel.setName(roleName);

        userModel = new UserModel(
                userId,
                "Test User",
                "user",
                123,
                "33444444",
                LocalDate.of(1990, 1, 1), // Fecha de nacimiento (debe ser mayor de 18 años)
                email,
                password,
                roleModel
        );
        userModel.setEmail(email);
        userModel.setRole(roleModel);
    }

    @Test
    void loginSuccess() {
        // Configurar comportamiento del mock
        when(authPersistencePort.authenticate(email, password)).thenReturn(userModel);
        when(authPersistencePort.generateToken(email, roleName, userId)).thenReturn(token);

        // Ejecutar método a probar
        LoginDomainResponse response = authUseCase.login(email, password);

        // Verificar resultado
        assertNotNull(response);
        assertEquals(token, response.getToken());
        assertEquals(roleName, response.getRole());

        // Verificar que los métodos mock fueron llamados con los parámetros correctos
        verify(authPersistencePort).authenticate(email, password);
        verify(authPersistencePort).generateToken(email, roleName, userId);
    }

    @Test
    void loginFailureInvalidCredentials() {
        // Configurar comportamiento del mock para simular fallo de autenticación
        when(authPersistencePort.authenticate(email, password))
                .thenThrow(new RuntimeException("Credenciales inválidas"));

        // Verificar que se lanza la excepción
        Exception exception = assertThrows(RuntimeException.class, () -> {
            authUseCase.login(email, password);
        });

        assertTrue(exception.getMessage().contains("Credenciales inválidas"));
        verify(authPersistencePort).authenticate(email, password);
        verify(authPersistencePort, never()).generateToken(anyString(), anyString(), anyLong());
    }
}