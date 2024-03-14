package org.example.Repository;

import org.example.Entity.Country;
import org.example.Entity.Region;

import java.util.List;
import java.util.Optional;

public interface CountryRepository {
    void save(Country country);
    List<Country> getAll();
    Optional<Country> getById(String id);
    Optional<Country> getByName(String name);
    void delete(Country country);
    boolean update(Country regionNew,Country regionOld);
}
