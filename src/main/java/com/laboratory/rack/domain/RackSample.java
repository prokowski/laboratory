package com.laboratory.rack.domain;

import com.laboratory.shared.ddd.AbstractEntity;
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
}
