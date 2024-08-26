package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

public record SlugResponse(
        String slug,
        String id,
        String uuid,
        String title
) {
}
