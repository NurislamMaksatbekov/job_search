package com.example.hw49.dto;

import com.example.hw49.entity.Resume;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Long id;
    private String title;
    private double requiredSalary;
    private boolean active;
    private String authorEmail;
    private CategoryDto category;
    private ExperienceDto experiences;
    private EducationDto educations;

}
