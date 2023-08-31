package com.laboratory.rack.domain

import com.laboratory.infrastructure.sequence.SequenceConfiguration
import com.laboratory.infrastructure.sequence.SequenceFacade
import com.laboratory.patient.domain.PatientConfiguration
import com.laboratory.patient.domain.PatientFacade
import com.laboratory.patient.query.PatientQueryRepository
import com.laboratory.rack.query.RackQuery
import com.laboratory.rack.query.RackQueryRepository
import com.laboratory.sample.domain.SampleConfiguration
import com.laboratory.sample.domain.SampleFacade
import com.laboratory.sample.domain.exceptions.NoSpaceInLaboratoryException
import com.laboratory.sample.query.SampleQueryRepository
import spock.lang.Specification

class RackFacadeSpec extends Specification {

    private def rackConfiguration = new RackConfiguration()
    private def patientConfiguration = new PatientConfiguration()
    private def sampleConfiguration = new SampleConfiguration()
    private def sequenceConfiguration = new SequenceConfiguration()

    private PatientQueryRepository patientQueryRepository = patientConfiguration.patientQueryRepositoryTest()

    private SampleQueryRepository sampleQueryRepository = sampleConfiguration.sampleQueryRepositoryTest()

    private RackQueryRepository rackQueryRepository = rackConfiguration.rackQueryRepositoryTest()

    private SequenceFacade sequenceFacade = sequenceConfiguration.sequenceFacadeTest()

    private RackFacade rackFacade = rackConfiguration.rackFacadeTest(sequenceFacade, sampleQueryRepository, patientQueryRepository)

    private PatientFacade patientFacade = patientConfiguration.patientFacadeTest(sequenceFacade)

    private SampleFacade sampleFacade = sampleConfiguration.sampleFacadeTest(sequenceFacade)

    def cleanup() {
        patientQueryRepository.deleteAll()
        sampleQueryRepository.deleteAll()
        rackQueryRepository.deleteAll()
    }

    def "should create rack"() {
        when: "create rack"
        def rackId = rackFacade.create(5)

        then: "patient is created"
        RackQuery rack = rackQueryRepository.findByRackId(rackId)
        rack.rackId == rackId
        rack.capacity == 5
    }

    def "should create racks"() {
        when: "create racks"
        def rackId1 = rackFacade.create(5)
        def rackId2 = rackFacade.create(6)
        def rackId3 = rackFacade.create(7)

        then: "racks are created"
        Iterable<RackQuery> racks = rackQueryRepository.findAll()
        racks[0].rackId == rackId1
        racks[1].rackId == rackId2
        racks[2].rackId == rackId3
        racks.size() == 3
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
        rackFacade.assignSample(sampleId1)
        rackFacade.assignSample(sampleId2)
        rackFacade.assignSample(sampleId3)
        rackFacade.assignSample(sampleId4)

        then: "samples are assigned according to business logic"
        RackQuery rack1 = rackQueryRepository.findByRackId(rackId1)
        RackQuery rack2 = rackQueryRepository.findByRackId(rackId2)
        RackQuery rack3 = rackQueryRepository.findByRackId(rackId3)

        rack1.samples == [sampleId1, sampleId2]
        rack2.samples == [sampleId3]
        rack3.samples == [sampleId4]
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
        rackFacade.assignSample(sampleId1)
        rackFacade.assignSample(sampleId2)
        rackFacade.assignSample(sampleId3)

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
        rackFacade.assignSample(sampleId1)
        rackFacade.assignSample(sampleId2)
        rackFacade.assignSample(sampleId3)

        then: "there is no space in Laboratory"
        thrown(NoSpaceInLaboratoryException)
    }

}