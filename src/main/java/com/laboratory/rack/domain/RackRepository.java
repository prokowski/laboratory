package com.laboratory.rack.domain;


import com.laboratory.shared.ddd.RackId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

interface RackRepository extends CrudRepository<Rack, Long> {

    Rack findByRackId(@NonNull RackId rackId);
}
