package co.istad.microservice.elearningcoursemanagment.feature.category.dto;

public record PopularCategoryResponse (
        String icon,
        String name,
        Long totalCourse
){
}
