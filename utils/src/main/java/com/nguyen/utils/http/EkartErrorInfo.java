package com.nguyen.utils.http;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class EkartErrorInfo {
    private ZonedDateTime timestamp;
    private String path;
    private HttpStatus status;
    private String message;

    public EkartErrorInfo(HttpStatus status,String path, String message) {
        this.timestamp = ZonedDateTime.now();
        this.path = path;
        this.status = status;
        this.message = message;
    }

    public EkartErrorInfo() {
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
