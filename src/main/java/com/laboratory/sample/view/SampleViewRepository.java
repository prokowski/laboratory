package com.laboratory.sample.view;

import com.laboratory.shared.ddd.SampleId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

public interface SampleViewRepository extends CrudRepository<SampleView, Long> {

    SampleView findBySampleId(@NonNull SampleId sampleId);
}
