package org.lucas.releasenotes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
                @NotBlank(message = "Email is Required") @Email(message = "Invalid Email Format") String email,

                @NotBlank(message = "Username is Required") String username,

                @NotBlank(message = "Password is Required") @Size(min = 8, message = "Password Must be at Least 8 Characters") String password

) {
}
