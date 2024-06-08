package com.springboot.spring.school;

import org.springframework.stereotype.Service;


@Service
public class SchoolMapper {

    public School toSchool(SchoolDTO schoolDTO){
        return new School(schoolDTO.name());
    }

    public SchoolDTO toSchoolDTO(School school){
        return new SchoolDTO(school.getName());
    }
}
