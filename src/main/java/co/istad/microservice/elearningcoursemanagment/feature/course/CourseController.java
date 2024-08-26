package co.istad.microservice.elearningcoursemanagment.feature.course;


import co.istad.microservice.elearningcoursemanagment.feature.course.dto.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    private final CourseService courseService;


    @GetMapping
    public Page<?> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size,
            @Parameter(
                    in = ParameterIn.QUERY,
                    schema = @Schema(type = "string", allowableValues = {"SNIPPET", "CONTENT_DETAIL"})
            )
            @RequestParam(defaultValue = "SNIPPET") String part) {
        return courseService.getAllCourses(page, size, part);
    }

    @GetMapping("/slug/{slug}")
    public SlugResponse getCourseBySlug(@PathVariable String slug){
        return courseService.getAllCoursesSlug(slug);
    }

    @GetMapping("/private")
    public Page<CourseResponse> getAllPrivateCourses(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "25") int pageSize) {
        return courseService.getAllCoursesPrivate(pageNumber, pageSize);
    }

    @GetMapping("/public")
    public Page<CourseResponse> getAllPublicCourses(@RequestParam(defaultValue = "0") int pageNumber,
                                                    @RequestParam(defaultValue = "25") int pageSize) {
        return courseService.getAllCoursesPublic(pageNumber, pageSize);
    }

    @GetMapping("/free")
    public Page<CourseResponse> getAllFreeCourses(@RequestParam(defaultValue = "0") int pageNumber,
                                                  @RequestParam(defaultValue = "25") int pageSize) {
        return courseService.getAllCoursesFree(pageNumber, pageSize);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@RequestBody CourseRequest courseRequest) {
        courseService.createCourse(courseRequest);
    }

    @PostMapping("/{courseId}/section")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSectionCourse(@PathVariable String courseId , @RequestBody SectionRequest sectionRequest){
        courseService.createSectionCourse(courseId,sectionRequest);
    }

    @PostMapping("/{courseId}/video")
    @ResponseStatus(HttpStatus.CREATED)
    public void createVideoSection( @PathVariable String courseId , @RequestBody VideoRequest videoRequest){
        courseService.createVideoSection(courseId,videoRequest);
    }

    @PutMapping("{courseId}/video")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateVideoSection(@PathVariable String courseId , @RequestBody UpdateVideo updateVideo){
        courseService.updateVideoSection(courseId,updateVideo);
    }


    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable String id, @RequestBody CourseRequest courseRequest) {
        courseService.updateCourse(courseRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCourseById(@PathVariable String id) {
        courseService.deleteCourseById(id);
    }

    @PutMapping("/{id}/visibility")
    public void updateVisibilityById(@PathVariable String id, @RequestBody UpdateVisibility updateVisibility) {
        courseService.updateVisibilityById(id, updateVisibility);
    }

    @PutMapping("/{id}/thumbnail")
    public void updateThumbnailById(@PathVariable String id, @RequestBody UpdateThumbnail updateThumbnail) {
        courseService.updateThumbnailById(id, updateThumbnail);
    }

    @PutMapping("/{id}/is-paid")
    public void updateIsPaidById(@PathVariable String id, @RequestBody UpdateThumbnail updateThumbnail) {
        courseService.updateThumbnailById(id, updateThumbnail);
    }

    @PutMapping("/{id}/enable")
    public void enableCourseById(@PathVariable String id) {
        courseService.enableCourseById(id);
    }

    @PutMapping("/{id}/disable")
    public void disableCourseById(@PathVariable String id) {
        courseService.disableCourseById(id);
    }

}
