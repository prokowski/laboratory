package com.laboratory.sample.domain;

import com.laboratory.patient.query.PatientQuery;
import com.laboratory.rack.query.RackQuery;
import com.laboratory.sample.query.SampleQueryRepository;
import com.laboratory.shared.ddd.SampleId;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
class SampleAllocator {

    private final SampleQueryRepository sampleQueryRepository;

    RackQuery allocate(@NonNull PatientQuery patient, @NonNull Iterable<RackQuery> racks) {
        for(RackQuery rack : racks) {
            if(isLegal(patient, rack)) {
                return rack;
            }
        }

        throw new RuntimeException("There is no space in Laboratory!");
    }

    private boolean isLegal(@NonNull PatientQuery patient, @NonNull RackQuery rack) {
        if (!rack.hasEnoughCapacity()) {
            return false;
        }

        for (SampleId sampleId : rack.getSamples()) {
            PatientQuery patientDiff = sampleQueryRepository.findBySampleId(sampleId).getPatient();
            if (!patientDiff.isLegal(patient)) {
                return false;
            }
        }

        return true;
    }
}
