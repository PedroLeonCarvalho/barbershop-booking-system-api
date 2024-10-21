package com.secured_template.infra.exception;

import org.springframework.http.HttpStatus;

public class AppointmentTimeUnavailableException extends ApiException {


    public AppointmentTimeUnavailableException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}