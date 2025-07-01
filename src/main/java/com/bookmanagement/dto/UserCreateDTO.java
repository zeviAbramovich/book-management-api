package com.bookmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "DTO for creating a new user")
public class UserCreateDTO {
    @NotBlank(message = "Username must not be blank")
    @Schema(description = "Username for the user", example = "john_doe")
    private String username;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be valid")
    @Schema(description = "Email address", example = "john.doe@example.com")
    private String email;

    @NotBlank(message = "Full name must not be blank")
    @Schema(description = "Full name", example = "John Doe")
    private String fullName;
}
