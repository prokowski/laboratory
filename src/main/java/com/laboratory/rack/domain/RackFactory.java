package com.laboratory.rack.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.infrastructure.sequence.SequenceIdentifier;
import com.laboratory.shared.ddd.RackId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class RackFactory {

    private final SequenceFacade sequenceFacade;

    Rack create(int capacity) {
        return Rack.builder()
                .rackId(new RackId(sequenceFacade.nextSequence(SequenceIdentifier.RCK)))
                .capacity(capacity)
                .samples(new ArrayList<>())
                .build();
    }
}
