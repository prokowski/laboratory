package com.laboratory.patient.query;

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
@Getter
@Table(name = "patient")
public class PatientQuery extends AbstractEntity {

    @NaturalId
    @Embedded
    private PatientId patientId;

    private int age;

    private String company;

    private String cityDistrict;

    private String visionDefect;

}
