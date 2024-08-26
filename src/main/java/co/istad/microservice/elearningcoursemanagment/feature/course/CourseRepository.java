package co.istad.microservice.elearningcoursemanagment.feature.course;

import co.istad.microservice.elearningcoursemanagment.domain.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CourseRepository extends MongoRepository<Course,String> {

    Optional<Course> findBySlug(String slug );
}
