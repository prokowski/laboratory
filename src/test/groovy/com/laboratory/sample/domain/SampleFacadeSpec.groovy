package com.laboratory.sample.domain

import com.laboratory.infrastructure.sequence.SequenceConfiguration
import com.laboratory.patient.domain.PatientConfiguration
import com.laboratory.patient.domain.PatientFacade
import com.laboratory.sample.query.SampleQuery
import com.laboratory.sample.query.SampleQueryRepository
import spock.lang.Specification

class SampleFacadeSpec extends Specification {

    private def sampleConfiguration = new SampleConfiguration()
    private def patientConfiguration = new PatientConfiguration()
    private def sequenceConfiguration = new SequenceConfiguration()

    private SampleQueryRepository sampleQueryRepository = sampleConfiguration.sampleQueryRepositoryTest()

    private SampleFacade sampleFacade = sampleConfiguration.sampleFacadeTest(sequenceConfiguration.sequenceFacadeTest())

    private PatientFacade patientFacade = patientConfiguration.patientFacadeTest(sequenceConfiguration.sequenceFacadeTest())

    def cleanup() {
        sampleQueryRepository.deleteAll()
    }

    def "should create sample"() {
        given: "patient id"
        def patientId1 = patientFacade.create(20, "Company1", "CityDistrict1", "VisionDefect1")

        when: "create sample"
        def sampleId = sampleFacade.create(patientId1)

        then: "sample is created"
        SampleQuery sample = sampleQueryRepository.findBySampleId(sampleId)
        sample.sampleId == sampleId
        sample.patientId == patientId1
    }

    def "should create samples"() {
        given: "patient ids"
        def patientId1 = patientFacade.create(20, "Company1", "CityDistrict1", "VisionDefect1")
        def patientId2 = patientFacade.create(30, "Company2", "CityDistrict2", "VisionDefect2")
        def patientId3 = patientFacade.create(40, "Company3", "CityDistrict3", "VisionDefect3")

        when: "create sample"
        def sampleId1 = sampleFacade.create(patientId1)
        def sampleId2 = sampleFacade.create(patientId2)
        def sampleId3 = sampleFacade.create(patientId3)

        then: "samples are created"
        List<SampleQuery> samples =  sampleQueryRepository.findAll()
        samples[0].sampleId == sampleId1
        samples[1].sampleId == sampleId2
        samples[2].sampleId == sampleId3
        samples.size() == 3
    }

}