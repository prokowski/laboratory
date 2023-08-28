package com.laboratory.shared.ddd;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.laboratory.infrastructure.sequence.SequenceIdentifier.SAMPLE_IDENTIFIER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleId implements DomainId {
    private String sampleId;

    public SampleId(String id) {
        Preconditions.checkArgument(id.startsWith(SAMPLE_IDENTIFIER.getIdentifier()),
                "Invalid sample ID!");
        this.sampleId = id;
    }

    public String id() {
        return sampleId;
    }
}
