package com.devmatheusmarques.bibliotech_api.dto;

import com.devmatheusmarques.bibliotech_api.util.Status;

import java.util.Objects;

public class UserEditDTO {

    private String username;
    private String password;
    private String role;
    private Status status;

    public UserEditDTO() {
    }

    public UserEditDTO(String username, String password, String role, Status status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserEditDTO that = (UserEditDTO) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && role == that.role && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, role, status);
    }

    @Override
    public String toString() {
        return "UserEditDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
