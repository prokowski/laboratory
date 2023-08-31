package com.laboratory.sample.domain;

import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SampleFacade {

    private final SampleFactory factory;

    private final SampleRepository repository;


    public SampleId create(@NonNull PatientId patientId) {
        Sample sample = factory.create(patientId);
        repository.save(sample);
        return sample.getSampleId();
    }

}
