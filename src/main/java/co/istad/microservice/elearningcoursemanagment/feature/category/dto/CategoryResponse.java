package co.istad.microservice.elearningcoursemanagment.feature.category.dto;

public record CategoryResponse(
        String id,
        String uuid,
        String name,
//        boolean isStatus,
        String icon
) {
}
