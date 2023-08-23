package com.laboratory


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

/**
Intentionally not using {@link IntegrationSpecification}. That spec should check if full context can stand up.
 */

@SpringBootTest(classes = LaboratoryApplication.class)
class LaboratoryApplicationFullContextSpec extends Specification {

    def "context should stand up"() {
        expect:
        1==1
    }

}
