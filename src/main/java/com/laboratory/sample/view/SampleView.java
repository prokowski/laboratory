package com.laboratory.sample.view;

import com.laboratory.patient.view.PatientView;
import com.laboratory.sample.view.dto.SampleViewDto;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class SampleView extends AbstractEntity {

    @Embedded
    @Getter
    private SampleId sampleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private PatientView patient;

    public SampleViewDto toDto() {
        return SampleViewDto.builder()
                .sampleId(sampleId.id())
                .patientId(patient.getPatientId().id())
                .build();
    }
}
