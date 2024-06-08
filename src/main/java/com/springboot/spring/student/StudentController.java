package com.springboot.spring.student;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET
    @GetMapping("/students")
    public List<StudentResponseDTO> findAllStudent(){
        return studentService.findAllStudent();
    }

    // GET by ID
    @GetMapping("/students/{student-id}")
    public StudentResponseDTO findStudentById(@PathVariable("student-id") Integer id){
        return studentService.findStudentById(id);
    }

    // POST
    @PostMapping("/students")
    public StudentResponseDTO saveStudent(@Valid @RequestBody StudentDTO studentDTO){
        return studentService.saveStudent(studentDTO);
    }

    // Custom query GET
    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDTO> getStudentsByFirstName(@PathVariable("student-name") String name){
        return studentService.findStudentsByFirstName(name);
    }

    // Update with PUT
    @PutMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStudent(@PathVariable("student-id") Integer id, @Valid @RequestBody StudentDTO studentDTO){
        studentService.updateStudent(id, studentDTO);
    }
    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable("student-id") Integer id){
        studentService.deleteStudent(id);
    }



    // EXCEPTION HANDLER
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception
    ){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
