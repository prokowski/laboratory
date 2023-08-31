package com.laboratory.rack.domain;

import com.laboratory.patient.query.PatientQuery;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
class RackSample extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rackId")
    private Rack rack;

    @Embedded
    private SampleId sampleId;

    @Embedded
    private PatientId patientId;

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;

    public boolean isLegal(@NonNull PatientQuery patient) {
        return patient.getAge() != age
                && !patient.getCompany().equals(company)
                && !patient.getCityDistrict().equals(cityDistrict)
                && !patient.getVisionDefect().equals(visionDefect);
    }

}
