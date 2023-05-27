package xyz.andreafalco.gttrestapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.andreafalco.gttrestapi.data.entity.GttRequest;

@Repository
public interface GttRequestRepository extends CrudRepository<GttRequest, String> {
}
