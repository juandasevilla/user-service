package com.example.userservice.domain.model;

import com.example.userservice.domain.exceptions.RoleNullOrSpaceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class RoleModelTest {

    private final Long id = 1L;
    private final String name = "ADMIN";
    private final String description = "Administrator role";

    @Test
    void constructorShouldInitializeAllAttributes() {
        // Arrange & Act
        RoleModel role = new RoleModel(id, name, description);

        // Assert
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
        assertEquals(description, role.getDescription());
    }

    @Test
    void gettersShouldReturnCorrectValues() {
        // Arrange
        RoleModel role = new RoleModel(id, name, description);

        // Act & Assert
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
        assertEquals(description, role.getDescription());
    }

    @Test
    void setNameShouldUpdateNameWithValidValue() {
        // Arrange
        RoleModel role = new RoleModel(id, name, description);
        String newName = "USER";

        // Act
        role.setName(newName);

        // Assert
        assertEquals(newName, role.getName());
    }

    @Test
    void setDescriptionShouldUpdateDescriptionWithValidValue() {
        // Arrange
        RoleModel role = new RoleModel(id, name, description);
        String newDescription = "Standard user role";

        // Act
        role.setDescription(newDescription);

        // Assert
        assertEquals(newDescription, role.getDescription());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void setNameShouldThrowExceptionForInvalidValues(String invalidName) {
        // Arrange
        RoleModel role = new RoleModel(id, name, description);

        // Act & Assert
        assertThrows(RoleNullOrSpaceException.class, () -> {
            role.setName(invalidName);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void setDescriptionShouldThrowExceptionForInvalidValues(String invalidDescription) {
        // Arrange
        RoleModel role = new RoleModel(id, name, description);

        // Act & Assert
        assertThrows(RoleNullOrSpaceException.class, () -> {
            role.setDescription(invalidDescription);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void constructorShouldThrowExceptionForInvalidName(String invalidName) {
        // Act & Assert
        assertThrows(RoleNullOrSpaceException.class, () -> {
            new RoleModel(id, invalidName, description);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void constructorShouldThrowExceptionForInvalidDescription(String invalidDescription) {
        // Act & Assert
        assertThrows(RoleNullOrSpaceException.class, () -> {
            new RoleModel(id, name, invalidDescription);
        });
    }
}