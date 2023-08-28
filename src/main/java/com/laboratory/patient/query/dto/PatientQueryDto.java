package com.laboratory.patient.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientQueryDto {

    private String patientId;

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;

    private List<String> samples;
}
