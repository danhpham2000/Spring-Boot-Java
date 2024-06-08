package com.springboot.spring.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    private final SchoolMapper schoolMapper;

    public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
        this.schoolRepository = schoolRepository;
        this.schoolMapper = schoolMapper;
    }

    // GET all service
    public List<SchoolDTO> findAllSchool(){
        return schoolRepository.findAll()
                .stream()
                .map(schoolMapper::toSchoolDTO)
                .collect(Collectors.toList());
    }

    // GET single service
    public SchoolDTO findSchoolById(Integer id){
        return schoolRepository.findById(id)
                .map(schoolMapper::toSchoolDTO)
                .orElse(null);
    }

    // POST service
    public SchoolDTO saveSchool(SchoolDTO schoolDTO){
        var school = schoolMapper.toSchool(schoolDTO);
        schoolRepository.save(school);
        return schoolDTO;
    }
}
