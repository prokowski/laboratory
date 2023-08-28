package com.laboratory.patient.query;

import com.laboratory.patient.query.dto.PatientQueryDto;
import com.laboratory.sample.query.SampleQuery;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.PatientId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<SampleQuery> samples;

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
                .samples(samples.stream().map(s -> s.getSampleId().id()).collect(Collectors.toList()))
                .build();
    }
}
