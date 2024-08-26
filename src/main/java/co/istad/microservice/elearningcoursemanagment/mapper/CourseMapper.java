package co.istad.microservice.elearningcoursemanagment.mapper;


import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.domain.Course;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.CourseRequest;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.CourseResponse;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.CourseResponseDetail;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.SlugResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course courseRequestToCourse(CourseRequest courseRequest);

    CourseResponse courseToCourseResponse(Course course);

    CourseResponseDetail courseToCourseResponseDetail(Course course);

    // Add this method
    default String map(Category category) {
        return category.getName();
    }

    SlugResponse courseToSlugResponse(Course course);

}
