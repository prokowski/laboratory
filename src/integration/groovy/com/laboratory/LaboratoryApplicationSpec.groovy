package com.laboratory

import com.fasterxml.jackson.databind.ObjectMapper
import com.laboratory.patient.domain.dto.CreatePatientDto
import com.laboratory.rack.domain.dto.CreateRackDto
import com.laboratory.sample.domain.dto.CreateSampleDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@AutoConfigureMockMvc
@SpringBootTest(classes = LaboratoryApplication.class)
class LaboratoryApplicationSpec extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    ObjectMapper objectMapper

    def "happy path for assignment samples to racks"() {
        given:
        CreatePatientDto patient1 = CreatePatientDto.builder()
                .age(20)
                .company("Company1")
                .cityDistrict("CityDistrict1")
                .visionDefect("VisionDefect1")
                .build()

        CreatePatientDto patient2 = CreatePatientDto.builder()
                .age(20)
                .company("Company2")
                .cityDistrict("CityDistrict2")
                .visionDefect("VisionDefect2")
                .build()

        CreatePatientDto patient3 = CreatePatientDto.builder()
                .age(30)
                .company("Company3")
                .cityDistrict("CityDistrict3")
                .visionDefect("VisionDefect3")
                .build()

        CreateRackDto rack1 = CreateRackDto.builder()
                .capacity(5)
                .build()

        CreateRackDto rack2 = CreateRackDto.builder()
                .capacity(5)
                .build()

        CreateRackDto rack3 = CreateRackDto.builder()
                .capacity(5)
                .build()

        CreateSampleDto sample1 = CreateSampleDto.builder()
                .patientId("PTN1")
                .build()

        CreateSampleDto sample2 = CreateSampleDto.builder()
                .patientId("PTN2")
                .build()

        CreateSampleDto sample3 = CreateSampleDto.builder()
                .patientId("PTN3")
                .build()

        CreateSampleDto sample4 = CreateSampleDto.builder()
                .patientId("PTN3")
                .build()

        when: "create patients: PTN1, PTN2, PTN3"
        mvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient2)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/patients").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient3)))
                .andExpect(status().isOk())


        then: "system has patients: PTN1, PTN2, PTN3"
        mvc.perform(get("/api/patients/{id}", "PTN1")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.patientId").value("PTN1"))
                .andExpect(jsonPath("\$.age").value(20))
                .andExpect(jsonPath("\$.company").value("Company1"))
                .andExpect(jsonPath("\$.cityDistrict").value("CityDistrict1"))
                .andExpect(jsonPath("\$.visionDefect").value("VisionDefect1"))

        mvc.perform(get("/api/patients/{id}", "PTN2")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.patientId").value("PTN2"))
                .andExpect(jsonPath("\$.age").value(20))
                .andExpect(jsonPath("\$.company").value("Company2"))
                .andExpect(jsonPath("\$.cityDistrict").value("CityDistrict2"))
                .andExpect(jsonPath("\$.visionDefect").value("VisionDefect2"))

        mvc.perform(get("/api/patients/{id}", "PTN3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.patientId").value("PTN3"))
                .andExpect(jsonPath("\$.age").value(30))
                .andExpect(jsonPath("\$.company").value("Company3"))
                .andExpect(jsonPath("\$.cityDistrict").value("CityDistrict3"))
                .andExpect(jsonPath("\$.visionDefect").value("VisionDefect3"))

        when: "create racks: RCK1, RCK2, RCK3"
        mvc.perform(post("/api/racks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rack1)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/racks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rack2)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/racks").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(rack3)))
                .andExpect(status().isOk())

        then: "system has racks: RCK1, RCK2, RCK3"
        mvc.perform(get("/api/racks/{id}", "RCK1")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.rackId").value("RCK1"))
                .andExpect(jsonPath("\$.capacity").value(5))
                .andExpect(jsonPath("\$.samples").value([]))

        mvc.perform(get("/api/racks/{id}", "RCK2")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.rackId").value("RCK2"))
                .andExpect(jsonPath("\$.capacity").value(5))
                .andExpect(jsonPath("\$.samples").value([]))

        mvc.perform(get("/api/racks/{id}", "RCK3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.rackId").value("RCK3"))
                .andExpect(jsonPath("\$.capacity").value(5))
                .andExpect(jsonPath("\$.samples").value([]))

        when: "create samples: SMP1, SMP2, SMP3, SMP4"
        mvc.perform(post("/api/samples").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample1)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/samples").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample2)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/samples").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample3)))
                .andExpect(status().isOk())

        mvc.perform(post("/api/samples").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sample4)))
                .andExpect(status().isOk())

        then: "system has samples: SMP1, SMP2, SMP3, SMP4"
        mvc.perform(get("/api/samples/{id}", "SMP1")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP1"))
                .andExpect(jsonPath("\$.patientId").value("PTN1"))
                .andExpect(jsonPath("\$.rackId").value(null))

        mvc.perform(get("/api/samples/{id}", "SMP2")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP2"))
                .andExpect(jsonPath("\$.patientId").value("PTN2"))
                .andExpect(jsonPath("\$.rackId").value(null))

        mvc.perform(get("/api/samples/{id}", "SMP3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP3"))
                .andExpect(jsonPath("\$.patientId").value("PTN3"))
                .andExpect(jsonPath("\$.rackId").value(null))

        mvc.perform(get("/api/samples/{id}", "SMP3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP3"))
                .andExpect(jsonPath("\$.patientId").value("PTN3"))
                .andExpect(jsonPath("\$.rackId").value(null))

        and: "patients have samples"
        mvc.perform(get("/api/patients/{id}", "PTN1")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.patientId").value("PTN1"))
                .andExpect(jsonPath("\$.samples").value(["SMP1"]))

        mvc.perform(get("/api/patients/{id}", "PTN2")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.patientId").value("PTN2"))
                .andExpect(jsonPath("\$.samples").value(["SMP2"]))

        mvc.perform(get("/api/patients/{id}", "PTN3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.patientId").value("PTN3"))
                .andExpect(jsonPath("\$.samples").value(["SMP3", "SMP4"]))

        when: "assign samples to rack"
        mvc.perform(put("/api/samples/{id}/assignToRack", "SMP1")).andExpect(status().isOk())

        mvc.perform(put("/api/samples/{id}/assignToRack", "SMP2")).andExpect(status().isOk())

        mvc.perform(put("/api/samples/{id}/assignToRack", "SMP3")).andExpect(status().isOk())

        mvc.perform(put("/api/samples/{id}/assignToRack", "SMP4")).andExpect(status().isOk())

        then: "samples SMP1, SMP3 are assignment to rack RCK1 and sample SMP2, SMP4 are assignment to rack RCK2"
        mvc.perform(get("/api/samples/{id}", "SMP1")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP1"))
                .andExpect(jsonPath("\$.patientId").value("PTN1"))
                .andExpect(jsonPath("\$.rackId").value("RCK1"))

        mvc.perform(get("/api/samples/{id}", "SMP2")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP2"))
                .andExpect(jsonPath("\$.patientId").value("PTN2"))
                .andExpect(jsonPath("\$.rackId").value("RCK2"))

        mvc.perform(get("/api/samples/{id}", "SMP3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP3"))
                .andExpect(jsonPath("\$.patientId").value("PTN3"))
                .andExpect(jsonPath("\$.rackId").value("RCK1"))

        mvc.perform(get("/api/samples/{id}", "SMP4")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.sampleId").value("SMP4"))
                .andExpect(jsonPath("\$.patientId").value("PTN3"))
                .andExpect(jsonPath("\$.rackId").value("RCK2"))

        and: "racks have samples"
        mvc.perform(get("/api/racks/{id}", "RCK1")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.rackId").value("RCK1"))
                .andExpect(jsonPath("\$.capacity").value(5))
                .andExpect(jsonPath("\$.samples").value(["SMP1", "SMP3"]))

        mvc.perform(get("/api/racks/{id}", "RCK2")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.rackId").value("RCK2"))
                .andExpect(jsonPath("\$.capacity").value(5))
                .andExpect(jsonPath("\$.samples").value(["SMP2", "SMP4"]))

        mvc.perform(get("/api/racks/{id}", "RCK3")).andExpect(status().isOk())
                .andExpect(jsonPath("\$.rackId").value("RCK3"))
                .andExpect(jsonPath("\$.capacity").value(5))
                .andExpect(jsonPath("\$.samples").value([]))
    }
}
