package com.laboratory.sample.query;

import com.laboratory.patient.query.PatientQuery;
import com.laboratory.rack.query.RackQuery;
import com.laboratory.sample.query.dto.SampleQueryDto;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class SampleQuery extends AbstractEntity {

    @Embedded
    private SampleId sampleId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private PatientQuery patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rackId", referencedColumnName = "rackId")
    private RackQuery rack;

    public SampleQueryDto toDto() {
        return SampleQueryDto.builder()
                .sampleId(sampleId.id())
                .patientId(patient.getPatientId().id())
                .rackId(rack != null ? rack.getRackId().id() : null)
                .build();
    }
}
