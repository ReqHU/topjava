package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.AbstractBaseEntity;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class InMemoryBaseRepository<T extends AbstractBaseEntity> {

    private static final AtomicInteger counter = new AtomicInteger(START_SEQ);

    Map<Integer, T> map = new ConcurrentHashMap<>();

    public T save(T entry) {
        Objects.requireNonNull(entry, "Entry must not be null");
        if (entry.isNew()) {
            entry.setId(counter.incrementAndGet());
            map.put(entry.getId(), entry);
            return entry;
        }
        return map.computeIfPresent(entry.getId(), (id, oldT) -> entry);
    }

    public boolean delete(int id) {
        return map.remove(id) != null;
    }

    public Optional<T> get(int id) {
        return Optional.ofNullable(map.get(id));
    }

    Collection<T> getCollection() {
        return map.values();
    }
}