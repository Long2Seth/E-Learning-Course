package co.istad.microservice.elearningcoursemanagment.feature.category;

import co.istad.microservice.elearningcoursemanagment.domain.Category;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryRequest;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryResponse;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.CategoryUpdate;
import co.istad.microservice.elearningcoursemanagment.feature.category.dto.PopularCategoryResponse;

import java.util.List;

public interface CategoryService {

    /**
     * Get all categories
     */
    List<CategoryResponse> getAllCategories();

    List<PopularCategoryResponse> getPopularCategory();

    CategoryResponse findCategoryById(String categoryId);


    void createCategory(CategoryRequest categoryRequest);


    CategoryResponse updateCategory(String categoryId, CategoryUpdate categoryUpdate);


    void deleteCategory(String categoryId);

    void enableCategory(String categoryId);

    void disableCategory(String categoryId);


}
