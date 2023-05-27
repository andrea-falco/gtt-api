package xyz.andreafalco.gttrestapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.andreafalco.gttrestapi.data.entity.GttTime;

@Repository
public interface GttTimeRepository extends CrudRepository<GttTime, String> {
}
