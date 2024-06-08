package com.springboot.spring.school;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @PostMapping("/schools")
    public SchoolDTO saveSchool(
            @RequestBody SchoolDTO schoolDTO
    ){
        return schoolService.saveSchool(schoolDTO);
    }

    @GetMapping("/schools")
    public List<SchoolDTO> finAllSchool(){
        return schoolService.findAllSchool();
    }

    @GetMapping("/schools/{school-id}")
    public SchoolDTO findSchoolById(@PathVariable("school-id") Integer id){
        return schoolService.findSchoolById(id);
    }
}
