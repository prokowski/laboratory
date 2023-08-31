package com.laboratory.rack;

import com.laboratory.rack.domain.RackFacade;
import com.laboratory.rack.domain.dto.CreateRackDto;
import com.laboratory.rack.view.RackViewRepository;
import com.laboratory.rack.view.dto.RackViewDto;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/racks")
class RackController {

    private final RackFacade rackFacade;

    private final RackViewRepository rackViewRepository;

    @PostMapping
    @ResponseBody
    public String createRack(@RequestBody CreateRackDto createRack) {
        return rackFacade.create(createRack.getCapacity()).id();
    }

    @GetMapping(value = "{rackId}")
    @ResponseBody
    public RackViewDto getRack(@PathVariable RackId rackId) {
        return rackViewRepository.findByRackId(rackId).toDto();
    }

    @PutMapping(value = "/assignSample/{sampleId}")
    @ResponseBody
    public String assignToRack(@PathVariable String sampleId) {
        return rackFacade.assignSample(new SampleId(sampleId)).id();
    }
}
