package com.laboratory.patient.domain;

import com.laboratory.patient.query.PatientQuery;
import com.laboratory.shared.ddd.AbstractAggregateEntity;
import com.laboratory.shared.ddd.PatientId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class Patient extends AbstractAggregateEntity {

    @Getter(AccessLevel.PACKAGE)
    @Embedded
    private PatientId patientId;

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;

    PatientQuery toQuery() {
        return PatientQuery.builder()
                .patientId(patientId)
                .age(age)
                .company(company)
                .cityDistrict(cityDistrict)
                .visionDefect(visionDefect)
                .build();
    }

}
