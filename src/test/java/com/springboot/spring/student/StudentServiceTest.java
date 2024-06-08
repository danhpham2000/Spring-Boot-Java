package com.springboot.spring.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    // which service we want to test
    @InjectMocks
    private StudentService studentService;

    // declare dependencies
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_success_save_student(){
        // Given
        StudentDTO studentDTO = new StudentDTO(
                "Danh",
                "Pham",
                "danh@gmail.com",
                1);

        Student student = new Student(
                "Danh",
                "Pham",
                "danh@gmail.com",
                20);

        Student savedStudent = new Student(
                "Danh",
                "Pham",
                "danh@gmail.com",
                20);

        savedStudent.setId(1);

        // Mocks the call
        when(studentMapper.toStudent(studentDTO)).
                thenReturn(student);

        when(studentRepository.save(student))
                .thenReturn(savedStudent);

        when(studentMapper.toStudentResponseDTO(savedStudent))
                .thenReturn(new StudentResponseDTO(
                        "Danh", "Pham", "danh@gmail.com"));

        // When
        StudentResponseDTO studentResponseDTO = studentService.
                saveStudent(studentDTO);

        // Then
        assertEquals(studentDTO.firstName(), studentResponseDTO.firstName());
        assertEquals(studentDTO.lastName(), studentResponseDTO.lastName());
        assertEquals(studentDTO.email(), studentResponseDTO.email());

        verify(studentMapper, times(1))
                .toStudent(studentDTO);
        verify(studentRepository, times(1))
                .save(student);
        verify(studentMapper, times(1))
                .toStudentResponseDTO(savedStudent);
    }

    @Test
    public void should_return_list(){

        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("David",
                "Pham",
                "danh12@gmail.com",
                19));

        students.add(new Student("Danh",
                "Pham",
                "danh123@gmail.com",
                19));
        // Mocks the call
        when(studentRepository.findAll()).thenReturn(students);

        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("David",
                        "Pham",
                        "danh12@gmail.com"));

        // When
        List<StudentResponseDTO> studentResponseDTOS = studentService.findAllStudent();

        // Then
        assertEquals(students.size(), studentResponseDTOS.size());

        verify(studentRepository, times(1)).findAll();
    }

    // Find By ID Test
    @Test
    public void should_return_student_by_id(){
        // Given
        Integer id = 1;

        Student student = new Student(
                "Danh", "Pham", "danh@gmail.com", 19);

        // Mocks recall
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("Danh", "Pham",
                        "danh@gmail.com"));

        // Then
        StudentResponseDTO studentResponseDTO = studentService.findStudentById(id);

        assertEquals(studentResponseDTO.firstName(), student.getFirstName());
        assertEquals(studentResponseDTO.lastName(), student.getLastName());
        assertEquals(studentResponseDTO.email(), student.getEmail());

        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    public void should_return_student_list_by_name(){
        // Given
        String name = "Danh";
        List<Student> students = new ArrayList<>();
        students.add(new Student("David",
                "Pham",
                "danh12@gmail.com",
                19));

        students.add(new Student("Danh",
                "Pham",
                "danh123@gmail.com",
                19));

        // When
        when(studentRepository.findAllByFirstNameContaining(name)).thenReturn(students);
        when(studentMapper.toStudentResponseDTO(any(Student.class)))
                .thenReturn(new StudentResponseDTO("David",
                        "Pham", "danh12@gmai.com"));
        List<StudentResponseDTO> studentResponseDTOList = studentService.findStudentsByFirstName(name);

        // Then
        assertEquals(studentResponseDTOList.size(), students.size());

        // Verify
        verify(studentRepository, times(1)).findAllByFirstNameContaining(name);
    }

    @Test
    public void should_update_student(){
        // Given
        Integer id = 1;
        StudentDTO studentDTO = new StudentDTO("David", "Pham",
                "david@gmail", 1);

    }

    @Test
    public void should_delete_student_by_id(){

        // Given
        Integer id = 1;
        doNothing().when(studentRepository).deleteById(id);

        // When
        studentService.deleteStudent(id);

        // Verify
        verify(studentRepository, times(1)).deleteById(id);

    }
}