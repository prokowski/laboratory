package com.laboratory.rack.view;

import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rack_sample")
public class RackSampleView extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rackId")
    private RackView rack;

    @Getter
    @Embedded
    private SampleId sampleId;
}
