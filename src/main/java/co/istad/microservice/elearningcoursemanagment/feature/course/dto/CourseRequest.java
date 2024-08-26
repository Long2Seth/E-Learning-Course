package co.istad.microservice.elearningcoursemanagment.feature.course.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseRequest(
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must be between 2 and 100 characters")
        String title,

        @NotBlank(message = "Slug is required")
        @Size(min = 2, max = 100, message = "Slug must be between 2 and 100 characters")
        String slug,

        @NotBlank(message = "Description is required")
        @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
        String description,

        @NotBlank(message = "Thumbnail URL is required")
        String thumbnail,

        @Min(value = 0, message = "Price must be greater than or equal to 0")
        double price,

        @NotBlank(message = "Content is required")
        String content,

        @NotBlank(message = "Category name is required")
        String categoryName
) {
}
