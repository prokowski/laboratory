package com.laboratory.shared.ddd;

import com.google.common.base.Preconditions;
import com.laboratory.infrastructure.sequence.SequenceIdentifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RackId implements DomainId {
    private String rackId;

    public RackId(String id) {
        Preconditions.checkArgument(id.startsWith(SequenceIdentifier.RCK.name()),
                "Invalid rack ID!");
        this.rackId = id;
    }

    public String id() {
        return rackId;
    }
}
