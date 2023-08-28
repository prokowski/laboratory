package com.laboratory.patient.domain;


import com.laboratory.patient.query.PatientQuery;
import com.laboratory.patient.query.PatientQueryRepository;
import com.laboratory.shared.ddd.PatientId;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class InMemoryPatientMapRepository {

    private static InMemoryPatientMapRepository instance;

    static synchronized InMemoryPatientMapRepository getInstance() {
        if(instance == null) {
            instance = new InMemoryPatientMapRepository();
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
    private final ConcurrentHashMap<PatientId, Patient> map = new ConcurrentHashMap<>();

}

class InMemoryPatientRepository implements PatientRepository {

    private final ConcurrentHashMap<PatientId, Patient> map = InMemoryPatientMapRepository.getInstance().getMap();

    public Patient save(Patient patient) {
        requireNonNull(patient);
        map.put(patient.getPatientId(), patient);
        return patient;
    }

    @Override
    public <S extends Patient> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Patient> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Patient> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Patient> findAllById(Iterable<Long> longs) {
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
    public void delete(Patient entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Patient> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}

class InMemoryPatientQueryRepository implements PatientQueryRepository {

    private final ConcurrentHashMap<PatientId, Patient> map = InMemoryPatientMapRepository.getInstance().getMap();

    private PatientQuery toQuery(Patient patient) throws IllegalAccessException {
        Field[] fields = patient.getClass().getDeclaredFields();

        Map<String, Object> fieldValues = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            fieldValues.put(field.getName(), field.get(patient));
        }

        return new PatientQuery(((PatientId) fieldValues.get("patientId")),
                (int) fieldValues.get("age"),
                (String) fieldValues.get("company"),
                (String) fieldValues.get("cityDistrict"),
                (String) fieldValues.get("visionDefect"),
                (List) fieldValues.get("samples"));
    }

    @Override
    public PatientQuery findByPatientId(PatientId id) {
        try {
            return toQuery(map.get(id));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public <S extends PatientQuery> S save(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends PatientQuery> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<PatientQuery> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<PatientQuery> findAll() {
        return map.values().stream().sorted(Comparator.comparing(p -> p.getPatientId().id())).map(patient -> {
            try {
                return toQuery(patient);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    @Override
    public Iterable<PatientQuery> findAllById(Iterable<Long> longs) {
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
    public void delete(PatientQuery entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends PatientQuery> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}
