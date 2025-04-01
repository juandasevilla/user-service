package com.example.userservice.domain.model;

import com.example.userservice.domain.exceptions.UserBirthDateException;
import com.example.userservice.domain.exceptions.UserEmailException;
import com.example.userservice.domain.exceptions.UserNullOrSpaceException;
import com.example.userservice.domain.exceptions.UserPhoneException;

import java.time.LocalDate;

import static com.example.userservice.domain.utils.DomainConstants.PHONE_MAX_LENGTH;

public class UserModel {
    private Long id;
    private String name;
    private String lastName;
    private Integer identification;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private RoleModel role;

    public UserModel(Long id, String name, String lastName, Integer identification, String phone, LocalDate birthDate, String email, String password, RoleModel role) {
        this.id = id;
        setName(name);
        setLastName(lastName);
        setIdentification(identification);
        setPhone(phone);
        setBirthDate(birthDate);
        setEmail(email);
        setPassword(password);
        setRole(role);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getIdentification() {
        return identification;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new UserNullOrSpaceException();
        }
        this.name = name;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new UserNullOrSpaceException();
        }
        this.lastName = lastName;
    }

    public void setIdentification(Integer identification) {
        if (identification == null) {
            throw new UserNullOrSpaceException();
        }
        this.identification = identification;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty() || phone.length() > PHONE_MAX_LENGTH || !phone.matches("^\\+?[0-9]{1,13}$")) {
            throw new UserPhoneException();
        }
        this.phone = phone;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null || LocalDate.now().minusYears(18).isBefore(birthDate)) {
            throw new UserBirthDateException();
        }
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new UserEmailException();
        }
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new UserNullOrSpaceException();
        }
        this.password = password;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }


}
