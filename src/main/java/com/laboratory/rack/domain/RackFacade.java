package com.laboratory.rack.domain;

import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class RackFacade {

    private final RackFactory factory;

    private final RackRepository repository;

    public RackId create(int capacity) {
        Rack rack = factory.create(capacity);
        repository.save(rack);
        return rack.getRackId();
    }

    public void addSample(@NonNull RackId rackId, @NonNull SampleId sampleId) {
        Rack rack = repository.findByRackId(rackId);
        rack.addSample(sampleId);
        repository.save(rack);
    }
}
