package xyz.andreafalco.gttrestapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.andreafalco.gttrestapi.data.entity.GttStop;

@Repository
public interface GttStopRepository extends CrudRepository<GttStop, String> {
}
