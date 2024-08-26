package co.istad.microservice.elearningcoursemanagment.mapper;


import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryRequest;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category categoryRequestToCategory(CategoryRequest categoryRequest);

    CategoryResponse categoryToCategoryResponse(Category category);

}
