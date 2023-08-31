package com.laboratory.rack.domain;

import com.google.common.base.Preconditions;
import com.laboratory.rack.query.RackQuery;
import com.laboratory.shared.ddd.AbstractAggregateEntity;
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

    @Getter(AccessLevel.PACKAGE)
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "rack")
    private List<RackSample> samples;

    void addSample(@NonNull SampleId sampleId) {
        Preconditions.checkArgument(hasEnoughCapacity(), "Rack {} is full!", rackId);
        samples.add(RackSample.builder().rack(this).sampleId(sampleId).build());
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
