package com.laboratory.sample.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.infrastructure.sequence.SequenceIdentifier;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class SampleFactory {

    private final SequenceFacade sequenceFacade;

    Sample create(@NonNull PatientId patientId) {
        return Sample.builder()
                .sampleId(new SampleId(sequenceFacade.nextSequence(SequenceIdentifier.SAMPLE_IDENTIFIER)))
                .patientId(patientId)
                .build();
    }
}
