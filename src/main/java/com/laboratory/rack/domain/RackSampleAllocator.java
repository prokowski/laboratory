package com.laboratory.rack.domain;

import com.laboratory.patient.query.PatientQuery;
import com.laboratory.patient.query.PatientQueryRepository;
import com.laboratory.sample.domain.exceptions.NoSpaceInLaboratoryException;
import com.laboratory.sample.query.SampleQuery;
import com.laboratory.sample.query.SampleQueryRepository;
import com.laboratory.shared.ddd.SampleId;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
class RackSampleAllocator {

    private final SampleQueryRepository sampleQueryRepository;
    private final PatientQueryRepository patientQueryRepository;

    Rack allocate(@NonNull SampleId sampleId, @NonNull Iterable<Rack> racks) {
        SampleQuery sample = sampleQueryRepository.findBySampleId(sampleId);
        PatientQuery patient = patientQueryRepository.findByPatientId(sample.getPatientId());

        for(Rack rack : racks) {
            if(isLegal(patient, rack)) {
                rack.addSample(sampleId, patient.getPatientId(), patient.getAge(), patient.getCompany(), patient.getCityDistrict(), patient.getVisionDefect());
                return rack;
            }
        }

        throw new NoSpaceInLaboratoryException();
    }

    private boolean isLegal(@NonNull PatientQuery patient, @NonNull Rack rack) {
        if (!rack.hasEnoughCapacity()) {
            return false;
        }

        return rack.isLegal(patient);
    }
}
