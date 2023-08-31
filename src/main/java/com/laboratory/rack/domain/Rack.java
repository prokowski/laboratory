package com.laboratory.rack.domain;

import com.google.common.base.Preconditions;
import com.laboratory.patient.query.PatientQuery;
import com.laboratory.rack.query.RackQuery;
import com.laboratory.shared.ddd.AbstractAggregateEntity;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class Rack extends AbstractAggregateEntity {

    @Getter(AccessLevel.PACKAGE)
    @Embedded
    private RackId rackId;

    private int capacity;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "rack")
    private List<RackSample> samples;

    void addSample(@NonNull SampleId sampleId, @NonNull PatientId patientId, int age, @NonNull String company, @NonNull String cityDistrict, @NonNull String visionDefect) {
        Preconditions.checkArgument(hasEnoughCapacity(), "Rack {} is full!", rackId);
        samples.add(RackSample.builder().rack(this)
                .sampleId(sampleId)
                .patientId(patientId)
                .age(age)
                .company(company)
                .cityDistrict(cityDistrict)
                .visionDefect(visionDefect)
                .build());
    }

    boolean isLegal(PatientQuery patient) {
        for (RackSample rackSample : samples) {
            if (!rackSample.isLegal(patient)) {
                return false;
            }
        }
        return true;
    }

    boolean hasEnoughCapacity() {
        return samples.size() < capacity;
    }

    RackQuery toQuery() {
        return RackQuery.builder()
                .rackId(rackId)
                .capacity(capacity)
                .samples(samples.stream().map(RackSample::getSampleId).collect(Collectors.toList()))
                .build();
    }

}
