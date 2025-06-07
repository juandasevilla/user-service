package com.example.userservice.domain.usecases;

import com.example.userservice.domain.exceptions.RoleAlreadyExistsException;
import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.domain.ports.out.RolePersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

    @Mock
    private RolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    private RoleModel roleModel;
    private final Long roleId = 1L;
    private final String roleName = "ADMIN";
    private final String roleDescription = "Administrator role";

    @BeforeEach
    void setUp() {
        roleModel = new RoleModel(roleId, roleName, roleDescription);
    }

    @Test
    void saveRoleSuccess() {
        // Simular que no existe el rol
        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(null);

        // Ejecutar método a probar
        roleUseCase.saveRole(roleModel);

        // Verificar que el método saveRole se llamó una vez con el roleModel
        verify(rolePersistencePort).saveRole(roleModel);
        // Verificar que getRoleByName se llamó una vez con el nombre del rol
        verify(rolePersistencePort).getRoleByName(roleName);
    }

    @Test
    void saveRoleFailWhenRoleAlreadyExists() {
        // Simular que ya existe un rol con ese nombre
        when(rolePersistencePort.getRoleByName(roleName)).thenReturn(roleModel);

        // Verificar que se lanza la excepción
        assertThrows(RoleAlreadyExistsException.class, () -> {
            roleUseCase.saveRole(roleModel);
        });

        // Verificar que getRoleByName se llamó, pero saveRole no
        verify(rolePersistencePort).getRoleByName(roleName);
        verify(rolePersistencePort, never()).saveRole(any(RoleModel.class));
    }

    @Test
    void getRolesSuccess() {
        // Crear lista de roles para el test
        RoleModel roleModel2 = new RoleModel(2L, "USER", "Standard user role");
        List<RoleModel> expectedRoles = Arrays.asList(roleModel, roleModel2);

        // Simular comportamiento del repositorio
        when(rolePersistencePort.getRoles()).thenReturn(expectedRoles);

        // Ejecutar método a probar
        List<RoleModel> actualRoles = roleUseCase.getRoles();

        // Verificaciones
        assertNotNull(actualRoles);
        assertEquals(2, actualRoles.size());
        assertEquals(expectedRoles, actualRoles);
        verify(rolePersistencePort).getRoles();
    }

    @Test
    void getRolesEmptyList() {
        // Simular lista vacía
        when(rolePersistencePort.getRoles()).thenReturn(Arrays.asList());

        // Ejecutar y verificar
        List<RoleModel> roles = roleUseCase.getRoles();

        assertNotNull(roles);
        assertTrue(roles.isEmpty());
        verify(rolePersistencePort).getRoles();
    }
}