package com.springboot.spring.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


// Interact with database, create custom interaction
public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findAllByFirstNameContaining(String p);

}
