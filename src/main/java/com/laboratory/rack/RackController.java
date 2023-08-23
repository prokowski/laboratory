package com.laboratory.rack;

import com.laboratory.rack.domain.RackFacade;
import com.laboratory.rack.domain.dto.CreateRackDto;
import com.laboratory.rack.query.RackQueryRepository;
import com.laboratory.rack.query.dto.RackQueryDto;
import com.laboratory.shared.ddd.RackId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/racks")
class RackController {

    private final RackFacade rackFacade;

    private final RackQueryRepository rackQueryRepository;

    @PostMapping
    @ResponseBody
    public String createRack(@RequestBody CreateRackDto createRack) {
        return rackFacade.create(createRack.getCapacity()).id();
    }

    @GetMapping(value = "{rackId}")
    @ResponseBody
    public RackQueryDto getRack(@PathVariable RackId rackId) {
        return rackQueryRepository.findByRackId(rackId).toDto();
    }
}
