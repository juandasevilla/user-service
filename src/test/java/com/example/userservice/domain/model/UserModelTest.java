package com.example.userservice.domain.model;

import com.example.userservice.domain.exceptions.UserBirthDateException;
import com.example.userservice.domain.exceptions.UserEmailException;
import com.example.userservice.domain.exceptions.UserNullOrSpaceException;
import com.example.userservice.domain.exceptions.UserPhoneException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserModelTest {

    private Long id;
    private String name;
    private String lastName;
    private Integer identification;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private RoleModel role;

    @BeforeEach
    void setUp() {
        id = 1L;
        name = "Juan";
        lastName = "Perez";
        identification = 12345678;
        phone = "3001234567";
        birthDate = LocalDate.of(1990, 1, 1);
        email = "juan.perez@example.com";
        password = "securePassword123";
        role = new RoleModel(1L, "USER", "Regular user");
    }

    @Test
    void constructorShouldInitializeAllAttributes() {
        // Arrange & Act
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(lastName, user.getLastName());
        assertEquals(identification, user.getIdentification());
        assertEquals(phone, user.getPhone());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    void gettersShouldReturnCorrectValues() {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(lastName, user.getLastName());
        assertEquals(identification, user.getIdentification());
        assertEquals(phone, user.getPhone());
        assertEquals(birthDate, user.getBirthDate());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void setNameShouldThrowExceptionForInvalidValues(String invalidName) {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserNullOrSpaceException.class, () -> {
            user.setName(invalidName);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void setLastNameShouldThrowExceptionForInvalidValues(String invalidLastName) {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserNullOrSpaceException.class, () -> {
            user.setLastName(invalidLastName);
        });
    }

    @Test
    void setIdentificationShouldThrowExceptionForNullValue() {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserNullOrSpaceException.class, () -> {
            user.setIdentification(null);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "abc123", "123456789012345", "+1234abc"})
    void setPhoneShouldThrowExceptionForInvalidValues(String invalidPhone) {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserPhoneException.class, () -> {
            user.setPhone(invalidPhone);
        });
    }

    @Test
    void setBirthDateShouldThrowExceptionForNullValue() {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserBirthDateException.class, () -> {
            user.setBirthDate(null);
        });
    }

    @Test
    void setBirthDateShouldThrowExceptionForUnder18() {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);
        LocalDate underage = LocalDate.now().minusYears(17);

        // Act & Assert
        assertThrows(UserBirthDateException.class, () -> {
            user.setBirthDate(underage);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "notAnEmail", "email@", "@domain.com"})
    void setEmailShouldThrowExceptionForInvalidValues(String invalidEmail) {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserEmailException.class, () -> {
            user.setEmail(invalidEmail);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  "})
    void setPasswordShouldThrowExceptionForInvalidValues(String invalidPassword) {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        // Act & Assert
        assertThrows(UserNullOrSpaceException.class, () -> {
            user.setPassword(invalidPassword);
        });
    }

    @Test
    void setValidFieldsShouldUpdateCorrectly() {
        // Arrange
        UserModel user = new UserModel(id, name, lastName, identification,
                phone, birthDate, email, password, role);

        String newName = "Maria";
        String newLastName = "Lopez";
        Integer newIdentification = 87654321;
        String newPhone = "3109876543";
        LocalDate newBirthDate = LocalDate.of(1985, 5, 15);
        String newEmail = "maria.lopez@example.com";
        String newPassword = "newPassword456";
        RoleModel newRole = new RoleModel(2L, "ADMIN", "Administrator");

        // Act
        user.setName(newName);
        user.setLastName(newLastName);
        user.setIdentification(newIdentification);
        user.setPhone(newPhone);
        user.setBirthDate(newBirthDate);
        user.setEmail(newEmail);
        user.setPassword(newPassword);
        user.setRole(newRole);

        // Assert
        assertEquals(newName, user.getName());
        assertEquals(newLastName, user.getLastName());
        assertEquals(newIdentification, user.getIdentification());
        assertEquals(newPhone, user.getPhone());
        assertEquals(newBirthDate, user.getBirthDate());
        assertEquals(newEmail, user.getEmail());
        assertEquals(newPassword, user.getPassword());
        assertEquals(newRole, user.getRole());
    }
}