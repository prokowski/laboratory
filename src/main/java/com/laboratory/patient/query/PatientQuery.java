package com.laboratory.patient.query;

import com.laboratory.patient.query.dto.PatientQueryDto;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.PatientId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Builder
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class PatientQuery extends AbstractEntity {

    @NaturalId
    @Getter
    @Embedded
    private PatientId patientId;

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;

    public boolean isLegal(@NonNull PatientQuery patient) {
        return patient.age != age
                && !patient.company.equals(company)
                && !patient.cityDistrict.equals(cityDistrict)
                && !patient.visionDefect.equals(visionDefect);
    }

    public PatientQueryDto toDto() {
        return PatientQueryDto.builder()
                .patientId(patientId.id())
                .age(age)
                .company(company)
                .cityDistrict(cityDistrict)
                .visionDefect(visionDefect)
                .build();
    }
}
