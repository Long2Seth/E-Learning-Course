package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

import java.util.List;

public record CourseResponse(
        String id,
        String uuid,
        String title,
        String slug,
        String description,
        String thumbnail,
        double price,
        String content,
        String categoryName,
        String updatedAt
) {
}
