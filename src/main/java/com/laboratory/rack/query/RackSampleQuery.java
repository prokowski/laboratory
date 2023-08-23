package com.laboratory.rack.query;

import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rack_sample")
public class RackSampleQuery extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="rackId")
    private RackQuery rack;

    @Getter
    @Embedded
    private SampleId sampleId;
}
