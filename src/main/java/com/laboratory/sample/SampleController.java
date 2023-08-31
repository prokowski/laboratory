package com.laboratory.sample;

import com.laboratory.sample.domain.SampleFacade;
import com.laboratory.sample.domain.dto.CreateSampleDto;
import com.laboratory.sample.view.SampleViewRepository;
import com.laboratory.sample.view.dto.SampleViewDto;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/samples")
class SampleController {

    private final SampleFacade sampleFacade;

    private final SampleViewRepository sampleViewRepository;

    @PostMapping
    @ResponseBody
    public String createSample(@RequestBody CreateSampleDto createSample) {
        return sampleFacade.create(new PatientId(createSample.getPatientId())).id();
    }

    @GetMapping(value = "{sampleId}")
    @ResponseBody
    public SampleViewDto getSample(@PathVariable String sampleId) {
        return sampleViewRepository.findBySampleId(new SampleId(sampleId)).toDto();
    }


}
