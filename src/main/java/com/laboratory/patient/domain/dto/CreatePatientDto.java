package com.laboratory.patient.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatientDto {

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;
}
