package org.lucas.releasenotes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto(
        @NotBlank(message = "Username cannot be empty")
        String username,

        @NotBlank
        @Email(message = "Invalid Email Format")
        String email
) {
}
