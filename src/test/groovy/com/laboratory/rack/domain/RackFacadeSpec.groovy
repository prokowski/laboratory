package com.laboratory.rack.domain

import com.laboratory.infrastructure.sequence.SequenceConfiguration
import com.laboratory.rack.query.RackQuery
import com.laboratory.rack.query.RackQueryRepository
import spock.lang.Specification

class RackFacadeSpec extends Specification {

    private def rackConfiguration = new RackConfiguration()
    private def sequenceConfiguration = new SequenceConfiguration()

    private RackFacade rackFacade =
            rackConfiguration.rackFacadeTest(sequenceConfiguration.sequenceFacadeTest())

    private RackQueryRepository rackQueryRepository =
            rackConfiguration.rackQueryRepositoryTest()

    def cleanup() {
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

}