package com.laboratory.rack.view;

import com.laboratory.rack.view.dto.RackViewDto;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rack")
public class RackView extends AbstractEntity {

    @NaturalId
    @Getter
    @Embedded
    private RackId rackId;

    private int capacity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rack")
    private List<RackSampleView> samples;

    public List<SampleId> getSamples() {
        return samples.stream().map(RackSampleView::getSampleId).collect(Collectors.toList());
    }

    public boolean hasEnoughCapacity() {
        return samples.size() < capacity;
    }

    public RackViewDto toDto() {
        return RackViewDto.builder()
                .rackId(rackId.id())
                .capacity(capacity)
                .samples(samples.stream().map(r -> r.getSampleId().id()).collect(Collectors.toList()))
                .build();
    }
}
