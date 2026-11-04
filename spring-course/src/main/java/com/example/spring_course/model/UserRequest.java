package com.example.spring_course.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank
    @Schema(description = "Name of the user", example = "Mario")
    private String name;
    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Min(value = 1, message = "Age must be greater than 1")
    @NotNull
    private int age;
    @NotBlank
    @Pattern(regexp = "([A-Z]{2})")
    @Schema(description = "Name of the city", example = "MI")
    private String city;
    @NotBlank
    @Pattern(regexp = "([A-Z]{2})")
    @Schema(description = "Name of the nation", example = "IT")
    private String nation;
}
