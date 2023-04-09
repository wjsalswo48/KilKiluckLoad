package mz.sixsense.road.repository;

import mz.sixsense.road.entity.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, String> {
    public List<Course> findAllByOrderByCourseIDAsc();
}
