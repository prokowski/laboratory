package com.laboratory.patient.view;

import com.laboratory.patient.view.dto.PatientViewDto;
import com.laboratory.sample.view.SampleView;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.PatientId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
public class PatientView extends AbstractEntity {

    @NaturalId
    @Getter
    @Embedded
    private PatientId patientId;

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
    private List<SampleView> samples;

    public PatientViewDto toDto() {
        return PatientViewDto.builder()
                .patientId(patientId.id())
                .age(age)
                .company(company)
                .cityDistrict(cityDistrict)
                .visionDefect(visionDefect)
                .samples(samples.stream().map(s -> s.getSampleId().id()).collect(Collectors.toList()))
                .build();
    }
}
