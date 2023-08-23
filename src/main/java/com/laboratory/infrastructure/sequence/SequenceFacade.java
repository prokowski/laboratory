package com.laboratory.infrastructure.sequence;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SequenceFacade {

	private final SequenceGenerator sequenceGenerator;

	public String nextSequence(SequenceIdentifier sequenceIdentifier) {
		Long sequence = sequenceGenerator.next(sequenceIdentifier.name());
		return SequenceIdentityCreator.createIdentity(sequenceIdentifier.name(), sequence);
	}
}
