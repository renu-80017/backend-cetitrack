package com.certitrack.backend.dto;

public class AuthResponse {

    private String message;
    private boolean success;
    private String name;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(String message, boolean success, String name, String role) {
        this.message = message;
        this.success = success;
        this.name = name;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}