package com.task.dto;

import com.task.model.enums.PRIORITY;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public record TaskRequestDTO(
        String title,
        String description,
        LocalDate dueDate,
        PRIORITY priority
){}



