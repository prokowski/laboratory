package com.laboratory.infrastructure.sequence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.laboratory.infrastructure.sequence.SequenceIdentifier.*;

class InMemorySequenceGenerator implements SequenceGenerator {

	private final Map<String, Sequence> map = new ConcurrentHashMap<String, Sequence>();

	public InMemorySequenceGenerator() {
		map.put(PATIENT_IDENTIFIER.getIdentifier(), new Sequence(PATIENT_IDENTIFIER.getIdentifier(), 0L));
		map.put(SAMPLE_IDENTIFIER.getIdentifier(), new Sequence(SAMPLE_IDENTIFIER.getIdentifier(), 0L));
		map.put(RACK_IDENTIFIER.getIdentifier(), new Sequence(RACK_IDENTIFIER.getIdentifier(), 0L));
	}

	@Override
	public long next(String name) {
		Sequence sequence = map.get(name);
		return sequence.next();
	}

}
