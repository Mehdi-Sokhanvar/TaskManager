package com.task.dto;

import java.util.UUID;

public record TaskResponseDTO(
        UUID uuid,
        String title
) {
}
