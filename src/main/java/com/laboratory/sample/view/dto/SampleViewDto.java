package com.laboratory.sample.view.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleViewDto {

    private String sampleId;

    private String patientId;

    private String rackId;
}
