package com.youtube.jwt.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;
@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private List<String> errors;

//    public ErrorResponse(String message, List<String> errors) {
//        this.message = message;
//        this.errors = errors;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public List<String> getErrors() {
//        return errors;
//    }
}
