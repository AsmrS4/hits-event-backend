package com.backend.event_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGuestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
}
