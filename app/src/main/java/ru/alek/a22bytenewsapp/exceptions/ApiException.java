package ru.alek.a22bytenewsapp.exceptions;

import ru.alek.a22bytenewsapp.domain.models.ServerResponse;

public class ApiException extends RuntimeException {

    private final String status, message, code;

    public ApiException(ServerResponse response){
        this.status = response.getStatus();
        this.message = response.getMessage();
        this.code = response.getCode();
    }

    public ApiException(String status, String message, String code){
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
