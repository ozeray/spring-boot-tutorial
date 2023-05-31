package com.ahmet.tutorial.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "User DTO details", title = "USER DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(description = "User first name")
    @NotEmpty(message = "First name should not be empty or whitespace")
    private String firstName;

    @Schema(description = "User last name")
    @NotEmpty(message = "Last name should not be empty or whitespace")
    private String lastName;

    @Schema(description = "User email address")
    @NotEmpty(message = "Email should not be empty or whitespace")
    @Email(message = "Email should be a well-formed email address")
    private String email;
}
