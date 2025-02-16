package com.example.mail.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response<T> {

    private static final String DATA_KEY = "data";
    private static final String MESSAGE_KEY = "message";
    private static final String STATUS_KEY = "status";
    private static final String TIMESTAMP_KEY = "timestamp";

    private final T data;
    private final String message;
    private final int status;
    private final long timestamp;
    private final Map<String, Object> additionalFields;

    public Response(T data, String message, HttpStatus httpStatus, Map<String, Object> additionalFields) {
        this.data = data;
        this.message = message;
        this.status = httpStatus.value();
        this.timestamp = System.currentTimeMillis();
        this.additionalFields = additionalFields != null ? additionalFields : new HashMap<>();
    }

    public static <T> ResponseEntity<Map<String, Object>> buildResponse(T data, String message, HttpStatus httpStatus) {
        return buildResponse(data, message, httpStatus, null);
    }

    public static <T> ResponseEntity<Map<String, Object>> buildResponse(T data, String message, HttpStatus httpStatus, Map<String, Object> additionalFields) {

        Response<T> response = new Response<>(data, message, httpStatus, additionalFields);
        Map<String, Object> responseMap = response.toMap();

        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

    public Map<String, Object> toMap() {
        Map<String, Object> response = new HashMap<>();
        response.put(DATA_KEY, this.data);
        response.put(MESSAGE_KEY, this.message);
        response.put(STATUS_KEY, this.status);
        response.put(TIMESTAMP_KEY, this.timestamp);

        if (this.additionalFields != null) {
            response.putAll(this.additionalFields);
        }

        return response;
    }

    public static <T> ResponseEntity<Map<String, Object>> success(T data, String message) {
        return buildResponse(data, message, HttpStatus.OK);
    }

    public static <T> ResponseEntity<Map<String, Object>> success(T data, String message, Map<String, Object> additionalFields) {
        return buildResponse(data, message, HttpStatus.OK, additionalFields);
    }

    public static ResponseEntity<Map<String, Object>> error(String message, HttpStatus httpStatus) {
        return buildResponse(null, message, httpStatus);
    }
}
