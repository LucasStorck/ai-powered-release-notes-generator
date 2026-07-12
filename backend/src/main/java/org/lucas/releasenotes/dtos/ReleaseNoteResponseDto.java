package org.lucas.releasenotes.dtos;

import java.time.LocalDateTime;

public record ReleaseNoteResponseDto(String markdownSummary, String modelUsed, LocalDateTime createdAt,
        UserResponseDto user) {
}
