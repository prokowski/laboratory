package com.laboratory.sample.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.sample.query.SampleQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SampleConfiguration {

    @Bean
    SampleFacade sampleFacade(SampleRepository sampleRepository, SequenceFacade sequenceFacade) {
        SampleFactory factory = new SampleFactory(sequenceFacade);

        return new SampleFacade(factory, sampleRepository);
    }

    SampleFacade sampleFacadeTest(SequenceFacade sequenceFacade) {
        SampleFactory factory = new SampleFactory(sequenceFacade);
        SampleRepository repository = new InMemorySampleRepository();

        return new SampleFacade(factory, repository);
    }

    SampleQueryRepository sampleQueryRepositoryTest() {
        return new InMemorySampleQueryRepository();
    }

}
