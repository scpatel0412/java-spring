package com.example.demo;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemoResponse {

    private String message;
    private int code;

    // Constructor
    public DemoResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
