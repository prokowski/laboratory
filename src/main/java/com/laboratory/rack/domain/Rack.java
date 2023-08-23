package com.laboratory.rack.domain;

import com.google.common.base.Preconditions;
import com.laboratory.shared.ddd.AbstractAggregateEntity;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    void addSample(@NonNull SampleId sampleId) {
        Preconditions.checkArgument(hasEnoughCapacity(), "Rack {} is full!", rackId);
        samples.add(RackSample.builder().rack(this).sampleId(sampleId).build());
    }

    private boolean hasEnoughCapacity() {
        return samples.size() < capacity;
    }

}
