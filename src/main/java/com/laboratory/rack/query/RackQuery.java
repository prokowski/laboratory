package com.laboratory.rack.query;

import com.laboratory.rack.query.dto.RackQueryDto;
import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rack")
public class RackQuery extends AbstractEntity {

    @NaturalId
    @Getter
    @Embedded
    private RackId rackId;

    private int capacity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rack")
    private List<RackSampleQuery> samples;

    public List<SampleId> getSamples() {
        return samples.stream().map(RackSampleQuery::getSampleId).collect(Collectors.toList());
    }

    public boolean hasEnoughCapacity() {
        return samples.size() < capacity;
    }

    public RackQueryDto toDto() {
        return RackQueryDto.builder()
                .rackId(rackId.id())
                .capacity(capacity)
                .samples(samples.stream().map(r -> r.getSampleId().id()).collect(Collectors.toList()))
                .build();
    }

}
