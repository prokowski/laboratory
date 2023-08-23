package com.laboratory.infrastructure.sequence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SequenceConfiguration {

	@Bean
	SequenceFacade sequenceFacade(SequenceGenerator sequenceGenerator) {
		return new SequenceFacade(sequenceGenerator);
	}


	SequenceFacade sequenceFacadeTest() {
		return new SequenceFacade(new InMemorySequenceGenerator());
	}
}
