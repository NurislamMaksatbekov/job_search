package com.example.hw49.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WhoResponded {
    private Long id;
    private String responderEmail;
    private Long respondedVacancyId;
    private LocalDateTime dateTime;
}
