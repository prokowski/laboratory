package com.laboratory.infrastructure.sequence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class InMemorySequenceGenerator implements SequenceGenerator {

	private final Map<String, Sequence> map = new ConcurrentHashMap<String, Sequence>();

	public InMemorySequenceGenerator() {
		map.put(SequenceIdentifier.PTN.name(), new Sequence(SequenceIdentifier.PTN.name(), 0L));
		map.put(SequenceIdentifier.SMP.name(), new Sequence(SequenceIdentifier.SMP.name(), 0L));
		map.put(SequenceIdentifier.RCK.name(), new Sequence(SequenceIdentifier.RCK.name(), 0L));
	}

	@Override
	public long next(String name) {
		Sequence sequence = map.get(name);
		return sequence.next();
	}

}
