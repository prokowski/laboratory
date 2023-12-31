package com.laboratory.patient;

import com.laboratory.patient.domain.PatientFacade;
import com.laboratory.patient.domain.dto.CreatePatientDto;
import com.laboratory.patient.view.PatientViewRepository;
import com.laboratory.patient.view.dto.PatientViewDto;
import com.laboratory.shared.ddd.PatientId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/patients")
class PatientController {

    private final PatientFacade patientFacade;

    private final PatientViewRepository patientViewRepository;

    @PostMapping
    @ResponseBody
    public String createPatient(@RequestBody CreatePatientDto createPatient) {
        return patientFacade.create(createPatient.getAge(), createPatient.getCompany(), createPatient.getCityDistrict(), createPatient.getVisionDefect()).id();

    }

    @GetMapping(value = "{patientId}")
    @ResponseBody
    public PatientViewDto getPatient(@PathVariable String patientId) {
        return patientViewRepository.findByPatientId(new PatientId(patientId)).toDto();
    }

}