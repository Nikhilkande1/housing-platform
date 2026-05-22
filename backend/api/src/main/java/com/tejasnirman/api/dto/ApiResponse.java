package com.tejasnirman.api.dto;

public class ApiResponse {
    private final Boolean success;
    private final String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    @java.lang.SuppressWarnings("all")
    public Boolean getSuccess() {
        return this.success;
    }

    @java.lang.SuppressWarnings("all")
    public String getMessage() {
        return this.message;
    }
}
