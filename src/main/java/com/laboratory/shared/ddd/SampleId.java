package com.laboratory.shared.ddd;

import com.google.common.base.Preconditions;
import com.laboratory.infrastructure.sequence.SequenceIdentifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleId implements DomainId {
    private String sampleId;

    public SampleId(String id) {
        Preconditions.checkArgument(id.startsWith(SequenceIdentifier.SMP.name()),
                "Invalid sample ID!");
        this.sampleId = id;
    }

    public String id() {
        return sampleId;
    }
}
