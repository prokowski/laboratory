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

//    public RackId assignToRack(@NonNull SampleId sampleId) {
//        Sample sample = repository.findBySampleId(sampleId);
//        PatientQuery patient = sampleQueryRepository.findBySampleId(sampleId).getPatient();
//        Iterable<RackQuery> racks = rackQueryRepository.findAll();
//
//        RackQuery rack = sampleAllocator.allocate(patient, racks);
//
//        rackFacade.addSample(rack.getRackId(), sampleId);
//        sample.assignToRack(rack.getRackId());
//        repository.save(sample);
//
//        return rack.getRackId();
//    }
}
