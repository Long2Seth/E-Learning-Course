package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

public record UpdateVideo(
        Integer sectionOrderNo,
        String lectureNo,
        Integer orderNo,
        String title,
        String fileName
) {
}
