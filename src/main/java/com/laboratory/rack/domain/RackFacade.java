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

    private final RackSampleAllocator rackSampleAllocator;

    public RackId create(int capacity) {
        Rack rack = factory.create(capacity);
        repository.save(rack);
        return rack.getRackId();
    }

    public RackId assignSample(@NonNull SampleId sampleId) {
        Iterable<Rack> racks = repository.findAll();

        Rack rack = rackSampleAllocator.allocate(sampleId, racks);

        repository.save(rack);

        return rack.getRackId();
    }
}
