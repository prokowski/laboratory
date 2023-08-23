package com.laboratory.sample.domain;


import com.laboratory.shared.ddd.SampleId;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

interface SampleRepository extends CrudRepository<Sample, Long> {

    Sample findBySampleId(@NonNull SampleId sampleId);

}
