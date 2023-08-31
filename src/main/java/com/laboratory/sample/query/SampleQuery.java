package com.laboratory.sample.query;

import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class SampleQuery extends AbstractEntity {

    @Embedded
    private SampleId sampleId;

    @Embedded
    private PatientId patientId;

}
