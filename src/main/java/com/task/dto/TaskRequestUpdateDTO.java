package com.task.dto;

import com.task.model.enums.PRIORITY;
import com.task.model.enums.STATUS;

import java.time.LocalDate;
import java.util.UUID;

public record TaskRequestUpdateDTO(
        String title,
        String description,
        LocalDate dueDate,
        PRIORITY priority,
        STATUS status
) {
}
