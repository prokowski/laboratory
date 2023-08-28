package com.laboratory.infrastructure.sequence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SequenceIdentifier {

	PATIENT_IDENTIFIER("PTN"),
	SAMPLE_IDENTIFIER("SMP"),
	RACK_IDENTIFIER("RCK");

	@Getter
	private final String identifier;

}
