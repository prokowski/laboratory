package com.laboratory.sample.domain;

import com.laboratory.shared.ddd.AbstractAggregateEntity;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.RackId;
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

    @Embedded
    private RackId rackId;

    void assignToRack(@NonNull RackId rackId) {
        this.rackId = rackId;
    }

}
