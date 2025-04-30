package com.task.dto;

public record ApiErrorResponse(
        int errorCode,
        String description
) {
}
