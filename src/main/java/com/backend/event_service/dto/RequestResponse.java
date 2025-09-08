package com.backend.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestResponse {
    private boolean status;
    private int code;
    private String message;
}
