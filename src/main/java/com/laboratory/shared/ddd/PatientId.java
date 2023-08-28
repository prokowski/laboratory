package com.laboratory.shared.ddd;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.laboratory.infrastructure.sequence.SequenceIdentifier.PATIENT_IDENTIFIER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientId implements DomainId {

    private String patientId;

    public PatientId(String id) {
        Preconditions.checkArgument(id.startsWith(PATIENT_IDENTIFIER.getIdentifier()),
                "Invalid patient ID!");
        this.patientId = id;
    }

    public String id() {
        return patientId;
    }
}
