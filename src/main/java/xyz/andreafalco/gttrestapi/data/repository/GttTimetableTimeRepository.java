package xyz.andreafalco.gttrestapi.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.andreafalco.gttrestapi.data.entity.GttTimetableTime;
import xyz.andreafalco.gttrestapi.data.entity.GttTimetableTimeKey;

@Repository
public interface GttTimetableTimeRepository extends CrudRepository<GttTimetableTime, GttTimetableTimeKey> {
}
