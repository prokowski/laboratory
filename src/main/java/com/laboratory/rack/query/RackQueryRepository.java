package com.laboratory.rack.query;

import com.laboratory.shared.ddd.RackId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface RackQueryRepository extends CrudRepository<RackQuery, Long> {

    RackQuery findByRackId(@NonNull RackId rackId);

}
