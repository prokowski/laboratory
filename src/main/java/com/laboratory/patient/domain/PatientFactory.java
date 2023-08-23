package com.laboratory.patient.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.infrastructure.sequence.SequenceIdentifier;
import com.laboratory.shared.ddd.PatientId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class PatientFactory {

    private final SequenceFacade sequenceFacade;

    Patient create(int age, @NonNull String company, @NonNull String cityDistrict, @NonNull String visionDefect) {
        return Patient.builder()
                .patientId(new PatientId(sequenceFacade.nextSequence(SequenceIdentifier.PTN)))
                .age(age)
                .company(company)
                .cityDistrict(cityDistrict)
                .visionDefect(visionDefect)
                .build();
    }
}
