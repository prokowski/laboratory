package com.laboratory.sample.query;

import com.laboratory.shared.ddd.SampleId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface SampleQueryRepository extends CrudRepository<SampleQuery, Long> {

    SampleQuery findBySampleId(@NonNull SampleId sampleId);

}
