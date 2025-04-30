package com.task.dto;

import com.task.model.enums.PRIORITY;
import com.task.model.enums.STATUS;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.UUID;

public record TaskResponse(
        UUID uuid,
        String title,
        String description,
        LocalDate dueDate,
        PRIORITY priority,
        STATUS status

) {
}
