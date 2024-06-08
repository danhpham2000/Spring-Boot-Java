package com.springboot.spring.student;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDTO studentDTO = new StudentDTO(
                "Danh",
                "Pham",
                "danh@gmail.com",
                1);
        Student student = studentMapper.toStudent(studentDTO);
        assertEquals(studentDTO.firstName(), student.getFirstName());
        assertEquals(studentDTO.lastName(), student.getLastName());
        assertEquals(studentDTO.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(studentDTO.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDTO_is_null(){
        var exception = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("The studentDTO should not be null", exception.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDTO(){
        // Given the student
        Student student = new Student(
                "Danh",
                "Pham",
                "danh@gmail.com",
                20);
        // When the DTO
        StudentResponseDTO studentResponseDTO = studentMapper.toStudentResponseDTO(student);
        assertEquals(student.getFirstName(), studentResponseDTO.firstName());
        assertEquals(student.getLastName(), studentResponseDTO.lastName());
        assertEquals(student.getEmail(), studentResponseDTO.email());

    }



}