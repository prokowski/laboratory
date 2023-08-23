package com.laboratory.sample.domain;


import com.laboratory.patient.query.PatientQueryRepository;
import com.laboratory.rack.query.RackQueryRepository;
import com.laboratory.sample.query.SampleQuery;
import com.laboratory.sample.query.SampleQueryRepository;
import com.laboratory.shared.ddd.PatientId;
import com.laboratory.shared.ddd.RackId;
import com.laboratory.shared.ddd.SampleId;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class InMemorySampleMapRepository {

    private static InMemorySampleMapRepository instance;

    static synchronized InMemorySampleMapRepository getInstance() {
        if(instance == null) {
            instance = new InMemorySampleMapRepository();
        }

        return instance;
    }

    static void resetInstance() {
        instance = null;
    }

    static void clear() {
        if(instance != null) {
            instance.map.clear();
        }
    }

    @Getter
    private final ConcurrentHashMap<SampleId, Sample> map = new ConcurrentHashMap<>();

}

class InMemorySampleRepository implements SampleRepository {

    private final ConcurrentHashMap<SampleId, Sample> map = InMemorySampleMapRepository.getInstance().getMap();

    public Sample save(Sample sample) {
        requireNonNull(sample);
        map.put(sample.getSampleId(), sample);
        return sample;
    }

    @Override
    public Sample findBySampleId(SampleId id) {
        return map.get(id);
    }

    @Override
    public <S extends Sample> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Sample> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Sample> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Sample> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Sample entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Sample> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}

class InMemorySampleQueryRepository implements SampleQueryRepository {

    private final ConcurrentHashMap<SampleId, Sample> map = InMemorySampleMapRepository.getInstance().getMap();

    private final RackQueryRepository rackQueryRepository;

    private final PatientQueryRepository patientQueryRepository;

    InMemorySampleQueryRepository(RackQueryRepository rackQueryRepository, PatientQueryRepository patientQueryRepository) {
        this.rackQueryRepository = rackQueryRepository;
        this.patientQueryRepository = patientQueryRepository;
    }

    private SampleQuery toQuery(Sample sample) throws IllegalAccessException {
        Field[] fields = sample.getClass().getDeclaredFields();

        Map<String, Object> fieldValues = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldValues.put(field.getName(), field.get(sample));
        }

        return new SampleQuery(((SampleId) fieldValues.get("sampleId")),
                fieldValues.get("patientId") != null ? patientQueryRepository.findByPatientId(((PatientId) fieldValues.get("patientId"))) : null,
                fieldValues.get("rackId") != null ? rackQueryRepository.findByRackId(((RackId) fieldValues.get("rackId"))) : null);
    }

    @Override
    public SampleQuery findBySampleId(SampleId id) {
        try {
            return toQuery(map.get(id));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public <S extends SampleQuery> S save(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends SampleQuery> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<SampleQuery> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<SampleQuery> findAll() {
        return map.values().stream().sorted(Comparator.comparing(s -> s.getSampleId().id())).map(sample -> {
            try {
                return toQuery(sample);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Iterable<SampleQuery> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(SampleQuery entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends SampleQuery> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}
