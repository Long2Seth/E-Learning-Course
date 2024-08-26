package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

import java.util.List;

public record SectionResponse(
        String title,
        Integer orderNo,
        List<VideoResponse> videos
) {
}
