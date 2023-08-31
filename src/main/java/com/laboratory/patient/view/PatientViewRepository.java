package com.laboratory.patient.view;

import com.laboratory.shared.ddd.PatientId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface PatientViewRepository extends CrudRepository<PatientView, Long> {
    PatientView findByPatientId(@NonNull PatientId patientId);
}


