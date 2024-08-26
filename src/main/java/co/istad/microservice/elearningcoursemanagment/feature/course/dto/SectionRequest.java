package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

import java.util.List;

public record SectionRequest(
        String title,
         Integer orderNo,
        List<VideoSectionRequest> videos
) {
}
