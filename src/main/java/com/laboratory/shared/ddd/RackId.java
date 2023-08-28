package com.laboratory.shared.ddd;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.laboratory.infrastructure.sequence.SequenceIdentifier.RACK_IDENTIFIER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RackId implements DomainId {
    private String rackId;

    public RackId(String id) {
        Preconditions.checkArgument(id.startsWith(RACK_IDENTIFIER.getIdentifier()),
                "Invalid rack ID!");
        this.rackId = id;
    }

    public String id() {
        return rackId;
    }
}
