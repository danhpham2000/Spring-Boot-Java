package com.springboot.spring.student;

public record StudentResponseDTO(
        String firstName,
        String lastName,
        String email
) {
}
