package co.istad.microservice.elearningcoursemanagment.feature.course;

import co.istad.microservice.elearningcoursemanagment.base.BaseFilter;
import co.istad.microservice.elearningcoursemanagment.feature.course.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {

    void createCourse(CourseRequest courseRequest);

    void createSectionCourse( String courseId , SectionRequest sectionRequest);

    void createVideoSection( String CourseId , VideoRequest videoRequest);


    void updateVideoSection( String courseId , UpdateVideo updateVideo);

    List<CourseResponse> getCourseByInstructorName(String instructorName);

    Page<?> getAllCourses(int page, int size, String part);

    Page<?> filterCourseByRequestBody(BaseFilter.FilterDto filterDto , int page , int size , String part);

    Page<?> filterCourseByParameter(int page, int size, String filterAnd, String filterOr, String orders, String part);


    SlugResponse getAllCoursesSlug( String slug );


    Page<CourseResponse> getAllCoursesPrivate( int page, int size);


    Page<CourseResponse> getAllCoursesPublic( int page, int size);


    Page<CourseResponse> getAllCoursesFree(int page, int size);


    CourseResponse getCourseById(String id);


    void updateCourse(CourseRequest courseRequest);

    void deleteCourseById(String id);

    void updateVisibilityById(String id , UpdateVisibility updateVisibility);

    void updateThumbnailById(String id , UpdateThumbnail updateThumbnail);

    void updateIsPaidById(String id , UpdateIsPaid updateIsPaid);

    void enableCourseById(String id);

    void disableCourseById(String id);




}
