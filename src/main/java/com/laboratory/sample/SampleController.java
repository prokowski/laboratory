package com.laboratory.sample;

import com.laboratory.sample.domain.SampleFacade;
import com.laboratory.sample.domain.dto.CreateSampleDto;
import com.laboratory.sample.query.SampleQueryRepository;
import com.laboratory.sample.query.dto.SampleQueryDto;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.SampleId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/samples")
class SampleController {

    private final SampleFacade sampleFacade;

    private final SampleQueryRepository sampleQueryRepository;

    @PostMapping
    @ResponseBody
    public String createSample(@RequestBody CreateSampleDto createSample) {
        return sampleFacade.create(new PatientId(createSample.getPatientId())).id();
    }

    @GetMapping(value = "{sampleId}")
    @ResponseBody
    public SampleQueryDto getSample(@PathVariable String sampleId) {
        return sampleQueryRepository.findBySampleId(new SampleId(sampleId)).toDto();
    }

    @PutMapping(value = "/{sampleId}/assignToRack")
    @ResponseBody
    public String assignToRack(@PathVariable String sampleId) {
        return sampleFacade.assignToRack(new SampleId(sampleId)).id();
    }
}
