package com.springboot.spring.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDTO(
        @NotEmpty(message = "First name should not be empty")
        String firstName,
        @NotEmpty(message = "Last name should not be empty")
        String lastName,
        @NotEmpty
        @Email(message = "Wrong email format")
        String email,
        Integer schoolId
) {

}
