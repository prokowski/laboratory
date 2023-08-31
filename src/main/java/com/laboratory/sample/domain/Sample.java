package com.laboratory.sample.domain;

import com.laboratory.sample.query.SampleQuery;
import com.laboratory.shared.ddd.AbstractAggregateEntity;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class Sample extends AbstractAggregateEntity {

    @Getter(AccessLevel.PACKAGE)
    @Embedded
    private SampleId sampleId;

    @Embedded
    private PatientId patientId;

    SampleQuery toQuery() {
        return SampleQuery.builder()
                .sampleId(sampleId)
                .patientId(patientId)
                .build();
    }


}
