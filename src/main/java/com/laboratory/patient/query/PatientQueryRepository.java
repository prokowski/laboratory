package com.laboratory.patient.query;

import com.laboratory.shared.ddd.PatientId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface PatientQueryRepository extends CrudRepository<PatientQuery, Long> {

    PatientQuery findByPatientId(@NonNull PatientId patientId);

}
