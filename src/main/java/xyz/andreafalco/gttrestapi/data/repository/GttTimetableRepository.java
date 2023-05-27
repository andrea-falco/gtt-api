package xyz.andreafalco.gttrestapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.andreafalco.gttrestapi.data.entity.GttTimetable;

@Repository
public interface GttTimetableRepository extends CrudRepository<GttTimetable, String> {
}
