package com.springboot.spring.student;


import com.springboot.spring.school.School;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    // GET all service
    public List<StudentResponseDTO> findAllStudent(){
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    // GET single service
    public StudentResponseDTO findStudentById(Integer id){
        return studentRepository.findById(id)
                .map(studentMapper::toStudentResponseDTO)
                .orElse(null);
    }

    // GET single firstName service
    public List<StudentResponseDTO> findStudentsByFirstName(String name){
        return studentRepository.findAllByFirstNameContaining(name)
                .stream().map(studentMapper::toStudentResponseDTO)
                .collect(Collectors.toList());
    }

    // POST service
    public StudentResponseDTO saveStudent(StudentDTO studentDTO){
        var student = studentMapper.toStudent(studentDTO);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDTO(savedStudent);
    }

    // PUT service
    public void updateStudent(Integer id, StudentDTO studentDTO){
        Student currentStudent = studentRepository.findById(id).orElse(null);
        var student = studentMapper.toStudent(studentDTO);
        if (currentStudent != null){
            currentStudent.setEmail(student.getEmail());
            currentStudent.setFirstName(student.getFirstName());
            currentStudent.setLastName(student.getLastName());
            currentStudent.setSchool(student.getSchool());
            studentRepository.save(currentStudent);
        }
    }

    // DELETE service
    public void deleteStudent(Integer id){
        studentRepository.deleteById(id);
    }


}
