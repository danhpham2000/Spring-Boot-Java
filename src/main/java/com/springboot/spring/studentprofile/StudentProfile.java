package com.springboot.spring.studentprofile;

import com.springboot.spring.student.Student;
import jakarta.persistence.*;

@Entity
public class StudentProfile {

    @Id
    @GeneratedValue
    private Integer id;
    private String bio;

    @OneToOne
    @JoinColumn(
            name = "student_id"
    )
    private Student student;

    public StudentProfile() {}

    public StudentProfile(String bio) {
        this.bio = bio;
    }
    public Integer getId() {
        return id;
    }
    public StudentProfile setId(Integer id) {
        this.id = id;
        return this;
    }
    public String getBio() {
        return bio;
    }
    public StudentProfile setBio(String bio) {
        this.bio = bio;
        return this;
    }
}
