package com.laboratory.rack.query;

import com.laboratory.shared.ddd.AbstractEntity;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.List;

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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name="rack_sample",
            joinColumns=@JoinColumn(name="rackId")
    )
    private List<SampleId> samples;

}
