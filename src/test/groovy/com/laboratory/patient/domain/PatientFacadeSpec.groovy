package com.laboratory.patient.domain

import com.laboratory.infrastructure.sequence.SequenceConfiguration
import com.laboratory.patient.query.PatientQuery
import com.laboratory.patient.query.PatientQueryRepository
import spock.lang.Specification

class PatientFacadeSpec extends Specification {

    private def patientConfiguration = new PatientConfiguration()
    private def sequenceConfiguration = new SequenceConfiguration()

    private PatientFacade patientFacade = patientConfiguration.patientFacadeTest(sequenceConfiguration.sequenceFacadeTest())
    private PatientQueryRepository patientQueryRepository = patientConfiguration.patientQueryRepositoryTest()

    def cleanup() {
        patientQueryRepository.deleteAll()
    }

    def "should create patient"() {
        when: "create patient"
        def patientId = patientFacade.create( 2, "Company1", "CityDistrict1", "VisionDefect1")

        then: "patient is created"
        PatientQuery patient = patientQueryRepository.findByPatientId(patientId)
        patient.patientId == patientId
        patient.age == 2
        patient.company == "Company1"
        patient.cityDistrict == "CityDistrict1"
        patient.visionDefect == "VisionDefect1"
    }

    def "should create patients"() {
        when: "create patient"
        def patientId1 = patientFacade.create( 20, "Company1", "CityDistrict1", "VisionDefect1")
        def patientId2 = patientFacade.create( 30, "Company2", "CityDistrict2", "VisionDefect2")
        def patientId3 = patientFacade.create( 40, "Company3", "CityDistrict3", "VisionDefect3")

        then: "patients are created"
        Iterable<PatientQuery> patients = patientQueryRepository.findAll()
        patients[0].patientId == patientId1
        patients[1].patientId == patientId2
        patients[2].patientId == patientId3
        patients.size() == 3
    }

}