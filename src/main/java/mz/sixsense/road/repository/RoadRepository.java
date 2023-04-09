package mz.sixsense.road.repository;

import mz.sixsense.road.entity.Course;
import mz.sixsense.road.entity.Road;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoadRepository extends CrudRepository<Road, String>, QuerydslPredicateExecutor<Road> {
    public List<Course> findAllByOrderByGuganNmAsc();
}
