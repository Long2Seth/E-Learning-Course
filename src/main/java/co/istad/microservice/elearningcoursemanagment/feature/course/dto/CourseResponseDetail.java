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
        double discount,
        Boolean isPaid,
        Boolean isDrafted,
        String content,
        String categoryName,
        String instructorName,
        List<SectionResponse> sections,
        String updatedAt
) {
}
