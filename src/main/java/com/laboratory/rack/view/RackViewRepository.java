package com.laboratory.rack.view;

import com.laboratory.shared.ddd.RackId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface RackViewRepository extends CrudRepository<RackView, Long> {

    RackView findByRackId(@NonNull RackId rackId);
}
