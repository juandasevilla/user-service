package com.example.userservice.domain.model;

import com.example.userservice.domain.exceptions.RoleNullOrSpaceException;

public class RoleModel {
    private String name;
    private String description;

    public RoleModel(String name, String description) {
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new RoleNullOrSpaceException();
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new RoleNullOrSpaceException();
        }
        this.description = description;
    }

}
