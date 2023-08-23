package com.laboratory.sample.query.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleQueryDto {

    String sampleId;

    String patientId;

    String rackId;
}
