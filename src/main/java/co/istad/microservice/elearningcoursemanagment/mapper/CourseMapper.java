package co.istad.microservice.elearningcoursemanagment.mapper;


import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.domain.Course;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.CourseRequest;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.CourseResponse;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.CourseResponseDetail;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.SlugResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    Course courseRequestToCourse(CourseRequest courseRequest);


    @Mapping(source = "category", target = "categoryName", qualifiedByName = "categoryToCategoryName")
    CourseResponse courseToCourseResponse(Course course);


    @Mapping(source = "category", target = "categoryName", qualifiedByName = "categoryToCategoryName")
    CourseResponseDetail courseToCourseResponseDetail(Course course);

    SlugResponse courseToSlugResponse(Course course);

    @Named("categoryToCategoryName")
    default String categoryToCategoryName(Category category) {
        return (category != null && !category.getName().isEmpty()) ? category.getName() : "Uncategorized";
    }

}
