package com.laboratory.patient.domain;

import com.laboratory.infrastructure.sequence.SequenceFacade;
import com.laboratory.patient.query.PatientQueryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PatientConfiguration {

    @Bean
    PatientFacade patientFacade(PatientRepository patientRepository, SequenceFacade sequenceFacade) {
        PatientFactory factory = new PatientFactory(sequenceFacade);
        return new PatientFacade(factory, patientRepository);
    }

    PatientFacade patientFacadeTest(SequenceFacade sequenceFacade) {
        PatientFactory factory = new PatientFactory(sequenceFacade);
        PatientRepository repository = new InMemoryPatientRepository();
        return new PatientFacade(factory, repository);
    }

    PatientQueryRepository patientQueryRepositoryTest() {
        return new InMemoryPatientQueryRepository();
    }

}
