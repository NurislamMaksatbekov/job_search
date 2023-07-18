package com.example.hw49.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class EducationDto {
    private Long id;
    private String education;
    private String placeOfStudy;
    private String studyPeriod;
}