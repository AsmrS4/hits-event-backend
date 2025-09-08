package com.backend.event_service.dto;

import lombok.Data;

@Data
public class RequestResponse {
    private boolean status;
    private int code;
    private String message;
}
