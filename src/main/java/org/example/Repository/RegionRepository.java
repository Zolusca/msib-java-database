package org.example.Repository;

import org.example.Entity.Region;

import java.util.List;
import java.util.Optional;

public interface RegionRepository {
    void save(Region region);
    List<Region> getAll();
    Optional<Region> getById(int id);
    Optional<Region> getByName(String name);
    void delete(Region region);
    boolean update(Region regionNew,Region regionOld);
}
