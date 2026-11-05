package repository;

import entity.NetstatSnapshot;

import java.util.Optional;

public interface SnapshotRepository {

    void save(NetstatSnapshot snapshot);

    Optional<NetstatSnapshot> findById(long id);
}
