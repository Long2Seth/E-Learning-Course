package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

public record VideoSectionRequest(
        String lectureNo,
        Integer orderNo,
        String title,
        String fileName
) {
}
