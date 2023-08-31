package com.laboratory.rack.domain;


import com.laboratory.rack.query.RackQuery;
import com.laboratory.rack.query.RackQueryRepository;
import com.laboratory.shared.ddd.RackId;
import lombok.Getter;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

class InMemoryRackMapRepository {

    private static InMemoryRackMapRepository instance;

    static synchronized InMemoryRackMapRepository getInstance() {
        if(instance == null) {
            instance = new InMemoryRackMapRepository();
        }

        return instance;
    }

    @Getter
    private final ConcurrentHashMap<RackId, Rack> map = new ConcurrentHashMap<>();

}

class InMemoryRackRepository implements RackRepository {

    private final ConcurrentHashMap<RackId, Rack> map = InMemoryRackMapRepository.getInstance().getMap();

    public Rack save(Rack rack) {
        requireNonNull(rack);
        map.put(rack.getRackId(), rack);
        return rack;
    }

    @Override
    public Rack findByRackId(RackId id) {
        return map.get(id);
    }

    @Override
    public <S extends Rack> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Rack> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<Rack> findAll() {
        return map.values().stream().sorted(Comparator.comparing(r -> r.getRackId().id()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Rack> findAllById(Iterable<Long> longs) {
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
    public void delete(Rack entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends Rack> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}

class InMemoryRackQueryRepository implements RackQueryRepository {

    private final ConcurrentHashMap<RackId, Rack> map = InMemoryRackMapRepository.getInstance().getMap();


    @Override
    public RackQuery findByRackId(RackId id) {
        return map.get(id).toQuery();
    }

    @Override
    public <S extends RackQuery> S save(S entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends RackQuery> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<RackQuery> findById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<RackQuery> findAll() {
        return map.values().stream().sorted(Comparator.comparing(r -> r.getRackId().id()))
                .map(Rack::toQuery).collect(Collectors.toList());
    }

    @Override
    public Iterable<RackQuery> findAllById(Iterable<Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(RackQuery entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends RackQuery> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        map.clear();
    }

}
