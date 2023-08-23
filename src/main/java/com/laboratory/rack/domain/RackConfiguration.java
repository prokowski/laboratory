package com.laboratory.rack.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.rack.query.RackQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RackConfiguration {

    @Bean
    RackFacade rackFacade(RackRepository rackRepository, SequenceFacade sequenceFacade) {
        RackFactory factory = new RackFactory(sequenceFacade);
        return new RackFacade(factory, rackRepository);
    }

    RackFacade rackFacadeTest(SequenceFacade sequenceFacade) {
        RackFactory factory = new RackFactory(sequenceFacade);
        RackRepository repository = new InMemoryRackRepository();
        return new RackFacade(factory, repository);
    }

    RackQueryRepository rackQueryRepositoryTest() {
        return new InMemoryRackQueryRepository();
    }

}
