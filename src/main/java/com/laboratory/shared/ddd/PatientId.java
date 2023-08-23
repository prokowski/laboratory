package com.laboratory.shared.ddd;

import com.google.common.base.Preconditions;
import com.laboratory.infrastructure.sequence.SequenceIdentifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientId implements DomainId {

    private String patientId;

    public PatientId(String id) {
        Preconditions.checkArgument(id.startsWith(SequenceIdentifier.PTN.name()),
                "Invalid patient ID!");
        this.patientId = id;
    }

    public String id() {
        return patientId;
    }
}
