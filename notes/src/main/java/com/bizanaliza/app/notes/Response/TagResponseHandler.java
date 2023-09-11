package com.bizanaliza.app.notes.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class TagResponseHandler {

    public static ResponseEntity<Object> responseBuilder(String responseMessage, HttpStatus httpStatus, Object responseObject){
        Map<String, Object> response = new HashMap<>();
        response.put("responseMessage",responseMessage);
        response.put("httpStatus", httpStatus);
        response.put("data", responseObject);

        return new ResponseEntity<>(response,httpStatus);
    }
}
