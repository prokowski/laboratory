package com.laboratory.sample.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.patient.query.PatientQueryRepository;
import com.laboratory.rack.domain.RackFacade;
import com.laboratory.rack.query.RackQueryRepository;
import com.laboratory.sample.query.SampleQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SampleConfiguration {

    @Bean
    SampleFacade sampleFacade(SampleRepository sampleRepository, RackFacade rackFacade, RackQueryRepository rackQueryRepository, SampleQueryRepository sampleQueryRepository, SequenceFacade sequenceFacade) {
        SampleFactory factory = new SampleFactory(sequenceFacade);
        SampleAllocator sampleAllocator = new SampleAllocator(sampleQueryRepository);
        return new SampleFacade(factory, sampleRepository, rackFacade, rackQueryRepository, sampleQueryRepository, sampleAllocator);
    }

    SampleFacade sampleFacadeTest(RackFacade rackFacade, RackQueryRepository rackQueryRepository, SampleQueryRepository sampleQueryRepository, SequenceFacade sequenceFacade) {
        SampleFactory factory = new SampleFactory(sequenceFacade);
        SampleRepository repository = new InMemorySampleRepository();
        SampleAllocator sampleAllocator = new SampleAllocator(sampleQueryRepository);
        return new SampleFacade(factory, repository, rackFacade, rackQueryRepository, sampleQueryRepository, sampleAllocator);
    }

    SampleQueryRepository sampleQueryRepositoryTest(RackQueryRepository rackQueryRepository, PatientQueryRepository patientQueryRepository) {
        return new InMemorySampleQueryRepository(rackQueryRepository, patientQueryRepository);
    }

}
