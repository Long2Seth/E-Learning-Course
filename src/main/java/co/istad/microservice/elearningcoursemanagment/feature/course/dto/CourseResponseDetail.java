package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

import java.util.List;

public record CourseResponseDetail(
        String id,
        String uuid,
        String title,
        String slug,
        String description,
        String thumbnail,
        double price,
        String content,
        String categoryName,
        List<SectionResponse> sections,
        String updatedAt
) {
}
