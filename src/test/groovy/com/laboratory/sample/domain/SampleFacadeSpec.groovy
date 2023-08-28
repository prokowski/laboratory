package com.laboratory.sample.domain

import com.laboratory.infrastructure.sequence.SequenceConfiguration
import com.laboratory.patient.domain.PatientConfiguration
import com.laboratory.patient.domain.PatientFacade
import com.laboratory.patient.query.PatientQueryRepository
import com.laboratory.rack.domain.RackConfiguration
import com.laboratory.rack.domain.RackFacade
import com.laboratory.rack.query.RackQuery
import com.laboratory.rack.query.RackQueryRepository
import com.laboratory.sample.domain.exceptions.NoSpaceInLaboratoryException
import com.laboratory.sample.query.SampleQuery
import com.laboratory.sample.query.SampleQueryRepository
import spock.lang.Specification

class SampleFacadeSpec extends Specification {

    private def sampleConfiguration = new SampleConfiguration()
    private def rackConfiguration = new RackConfiguration()
    private def patientConfiguration = new PatientConfiguration()
    private def sequenceConfiguration = new SequenceConfiguration()

    private SampleQueryRepository sampleQueryRepository = sampleConfiguration.sampleQueryRepositoryTest(rackConfiguration.rackQueryRepositoryTest(),
                patientConfiguration.patientQueryRepositoryTest() )

    private RackQueryRepository rackQueryRepository = rackConfiguration.rackQueryRepositoryTest()

    private PatientQueryRepository patientQueryRepository = patientConfiguration.patientQueryRepositoryTest()

    private SampleFacade sampleFacade = sampleConfiguration.sampleFacadeTest(
            rackConfiguration.rackFacadeTest(sequenceConfiguration.sequenceFacadeTest()),
                rackConfiguration.rackQueryRepositoryTest(),
                sampleConfiguration.sampleQueryRepositoryTest(rackConfiguration.rackQueryRepositoryTest(),
                        patientConfiguration.patientQueryRepositoryTest()),sequenceConfiguration.sequenceFacadeTest())


    private RackFacade rackFacade = rackConfiguration.rackFacadeTest(sequenceConfiguration.sequenceFacadeTest())

    private PatientFacade patientFacade = patientConfiguration.patientFacadeTest(sequenceConfiguration.sequenceFacadeTest())

    def cleanup() {
        sampleQueryRepository.deleteAll()
        rackQueryRepository.deleteAll()
        patientQueryRepository.deleteAll()
    }

    def "should create sample"() {
        given: "patient id"
        def patientId1 = patientFacade.create(20, "Company1", "CityDistrict1", "VisionDefect1")

        when: "create sample"
        def sampleId = sampleFacade.create(patientId1)

        then: "sample is created"
        SampleQuery sample = sampleQueryRepository.findBySampleId(sampleId)
        sample.sampleId == sampleId
        sample.patient.patientId == patientId1
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

    def "should assign samples to racks"() {
        given: "4 patients, 3 racks and 4 samples"
        def patientId1 = patientFacade.create(20, "Company1", "CityDistrict1", "VisionDefect1")
        def patientId2 = patientFacade.create(30, "Company2", "CityDistrict2", "VisionDefect2")
        def patientId3 = patientFacade.create(20, "Company1", "CityDistrict3", "VisionDefect3")
        def patientId4 = patientFacade.create(40, "Company1", "CityDistrict3", "VisionDefect3")

        def rackId1 = rackFacade.create(5)
        def rackId2 = rackFacade.create(5)
        def rackId3 = rackFacade.create(5)

        def sampleId1 = sampleFacade.create(patientId1)
        def sampleId2 = sampleFacade.create(patientId2)
        def sampleId3 = sampleFacade.create(patientId3)
        def sampleId4 = sampleFacade.create(patientId4)

        when: "assign samples to racks"
        sampleFacade.assignToRack(sampleId1)
        sampleFacade.assignToRack(sampleId2)
        sampleFacade.assignToRack(sampleId3)
        sampleFacade.assignToRack(sampleId4)

        then: "samples are assigned according to business logic"
        SampleQuery sample1 = sampleQueryRepository.findBySampleId(sampleId1)
        SampleQuery sample2 = sampleQueryRepository.findBySampleId(sampleId2)
        SampleQuery sample3 = sampleQueryRepository.findBySampleId(sampleId3)
        SampleQuery sample4 = sampleQueryRepository.findBySampleId(sampleId4)

        RackQuery rack1 = rackQueryRepository.findByRackId(rackId1)
        RackQuery rack2 = rackQueryRepository.findByRackId(rackId2)
        RackQuery rack3 = rackQueryRepository.findByRackId(rackId3)

        rack1.samples == [sampleId1, sampleId2]
        rack2.samples == [sampleId3]
        rack3.samples == [sampleId4]

        sample1.rack.rackId == rackId1
        sample2.rack.rackId == rackId1
        sample3.rack.rackId == rackId2
        sample4.rack.rackId == rackId3
    }

    def "should thrown 'There is no space in Laboratory!' (not enough racks)"() {
        given: "3 patients with the same age, 2 racks and 3 samples"
        def patientId1 = patientFacade.create(20, "Company1", "CityDistrict1", "VisionDefect1")
        def patientId2 = patientFacade.create(20, "Company2", "CityDistrict2", "VisionDefect2")
        def patientId3 = patientFacade.create(20, "Company3", "CityDistrict3", "VisionDefect3")

        rackFacade.create(5)
        rackFacade.create(5)

        def sampleId1 = sampleFacade.create(patientId1)
        def sampleId2 = sampleFacade.create(patientId2)
        def sampleId3 = sampleFacade.create(patientId3)

        when: "assign samples to racks"
        sampleFacade.assignToRack(sampleId1)
        sampleFacade.assignToRack(sampleId2)
        sampleFacade.assignToRack(sampleId3)

        then: "there is no space in Laboratory"
        thrown(NoSpaceInLaboratoryException)
    }

    def "should thrown 'There is no space in Laboratory!' (not enough rack capacity)"() {
        given: "3 patients, 1 rack with capacity (2) and 3 samples"
        def patientId1 = patientFacade.create(20, "Company1", "CityDistrict1", "VisionDefect1")
        def patientId2 = patientFacade.create(30, "Company2", "CityDistrict2", "VisionDefect2")
        def patientId3 = patientFacade.create(40, "Company3", "CityDistrict3", "VisionDefect3")

        rackFacade.create(2)

        def sampleId1 = sampleFacade.create(patientId1)
        def sampleId2 = sampleFacade.create(patientId2)
        def sampleId3 = sampleFacade.create(patientId3)

        when: "assign samples to racks"
        sampleFacade.assignToRack(sampleId1)
        sampleFacade.assignToRack(sampleId2)
        sampleFacade.assignToRack(sampleId3)

        then: "there is no space in Laboratory"
        thrown(NoSpaceInLaboratoryException)
    }
    
}