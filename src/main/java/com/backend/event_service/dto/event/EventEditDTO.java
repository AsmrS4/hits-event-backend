package com.backend.event_service.dto.event;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EventEditDTO {
    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 100, message = "Title length must be in range of 1 and 100 symbol")
    private String title;
    @Nullable
    @Size(max = 255, message = "Max available description length is 255 symbol")
    private String description;
    @NotBlank(message = "Location is required")
    @Size(max = 255, message = "Max available location length is 255 symbol")
    private String location;
    @NotBlank(message = "Date is required")
    private LocalDateTime date;
    @Nullable
    private LocalDateTime deadline;
}
