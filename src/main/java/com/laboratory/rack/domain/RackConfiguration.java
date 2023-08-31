package com.laboratory.rack.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.patient.query.PatientQueryRepository;
import com.laboratory.rack.query.RackQueryRepository;
import com.laboratory.sample.query.SampleQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RackConfiguration {

    @Bean
    RackFacade rackFacade(RackRepository rackRepository,
                          SampleQueryRepository sampleQueryRepository,
                          PatientQueryRepository patientQueryRepository,
                          SequenceFacade sequenceFacade) {
        RackFactory factory = new RackFactory(sequenceFacade);
        RackSampleAllocator rackSampleAllocator = new RackSampleAllocator(sampleQueryRepository, patientQueryRepository);

        return new RackFacade(factory, rackRepository, rackSampleAllocator);
    }

    RackFacade rackFacadeTest(SequenceFacade sequenceFacade,
                              SampleQueryRepository sampleQueryRepository,
                              PatientQueryRepository patientQueryRepository) {
        RackFactory factory = new RackFactory(sequenceFacade);
        RackRepository repository = new InMemoryRackRepository();
        RackSampleAllocator rackSampleAllocator = new RackSampleAllocator(sampleQueryRepository, patientQueryRepository);

        return new RackFacade(factory, repository, rackSampleAllocator);
    }

    RackQueryRepository rackQueryRepositoryTest() {
        return new InMemoryRackQueryRepository();
    }

}
