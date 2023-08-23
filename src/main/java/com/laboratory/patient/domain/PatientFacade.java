package com.laboratory.patient.domain;

import com.laboratory.shared.ddd.PatientId;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PatientFacade {

    private final PatientFactory factory;

    private final PatientRepository repository;

    public PatientId create(int age, @NonNull String company, @NonNull String cityDistrict, @NonNull String visionDefect) {
        Patient patient = factory.create(age, company, cityDistrict, visionDefect);
        repository.save(patient);
        return patient.getPatientId();
    }
}
